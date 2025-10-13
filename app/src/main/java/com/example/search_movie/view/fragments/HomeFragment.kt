package com.example.search_movie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.search_movie.databinding.FragmentHomeBinding
import com.example.search_movie.data.entity.Film
import com.example.search_movie.utils.AnimationHelper
import com.example.search_movie.utils.AutoDisposable
import com.example.search_movie.utils.addTo
import com.example.search_movie.view.MainActivity
import com.example.search_movie.view.rv_adapters.FilmListRecyclerAdapter
import com.example.search_movie.view.rv_adapters.TopSpacingItemDecoration
import com.example.search_movie.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import java.util.concurrent.TimeUnit


class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private val autoDisposable = AutoDisposable()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoDisposable.bindTo(lifecycle)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )

        initSearchView()
        initPullToRefresh()
        initRecyckler()

        viewModel.filmsListData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                filmsAdapter.addItems(list)
            }
            .addTo(autoDisposable)
        viewModel.showProgressBar
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.progressBar.isVisible = it
            }
            .addTo(autoDisposable)
    }

     private fun initPullToRefresh() {
          binding.pullToRefresh.setOnRefreshListener {
            filmsAdapter.items.clear()
            viewModel.getFilms()
            binding.pullToRefresh.isRefreshing = false
        }
    }
    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

    Observable.create(ObservableOnSubscribe<String> { subscriber ->
              binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                filmsAdapter.items.clear()
                subscriber.onNext(newText)
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                subscriber.onNext(query)
                return false
            }
        })
    })
    .subscribeOn(Schedulers.io())
    .map {
        it.toLowerCase(Locale.getDefault()).trim()
    }
    .debounce(800, TimeUnit.MILLISECONDS)
    .filter {
        viewModel.getFilms()
        it.isNotBlank()
    }
    .flatMap {
        viewModel.getSearchResult(it)
    }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeBy(
    onError = {
        Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
    },
    onNext = {
        filmsAdapter.addItems(it)
    }
    )
    .addTo(autoDisposable)
}

    private fun initRecyckler() {
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

}