package com.example.search_movie.data

import com.example.search_movie.R
import com.example.search_movie.domain.Film

class MainRepository {
    val filmsDataBase = listOf(
        Film(
            "Jurassic Park",
            R.drawable.jurassicpark,
            "An expansive rich man and professor persuades two paleontologists to come to an island off the coast of Costa Rica, where he has set up a Jurassic Park. The park is inhabited by long-extinct dinosaurs, recreated by the professor using blood samples from a fossil mosquito, which should be the highlight of the new attraction. There are several days left before the opening, and one of the employees, trying to steal priceless embryos, violates the security system, which, together with a thunderstorm, leads to a power outage and protective barriers. Just at the moment when the paleontologists and the professor's grandchildren went on a trial tour.",6.8f
        ),
        Film(
            "Home alone",
            R.drawable.homealone,
            "The McCallister family, mom, dad, and five children, are going to Paris for the Christmas holidays. More precisely, five of them were supposed to go, but upon arrival it turned out that in the rush they forgot to take their youngest Kevin with them. An eight-year-old boy is left alone at home. At first, he is even pleased with this opportunity, but two experienced robbers choose the McCallister house as their target for robbery.", 8.0f
        ),
        Film(
            "The Dark Knight",
            R.drawable.the_dark_knight,
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", 6.9f
        ),
        Film(
            "Pulp Fiction",
            R.drawable.pulp,
            "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",7.2f
        ),
        Film(
            "Avengers",
            R.drawable.avengers,
            "The earth is on the verge of enslavement, and only the best of the best can save humanity. The head of the international organization S.H.I.E.L.D. Nick Fury gathers outstanding defenders of justice and goodness. Under the leadership of Captain America, Iron Man, Thor, the Incredible Hulk, Hawkeye and Black Widow go to war with the invader.",9.5f
        ),
        Film(
            "Interstellar",
            R.drawable.interstellar,
            "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.", 3.2f
        ),
        Film(
            "Ghoustbusters",
            R.drawable.ghostbusters,
            "At the end of the twentieth century, it turns out that not only ordinary citizens live in New York, but also ... ghosts. A population of millions cannot withstand the onslaught of the supernatural. In the end, there is no one left in the path of countless monsters - except for three scientists, parapsychologists, who know everything about the other world, the truth is only in theory. And now they will have to leave their dusty classrooms and put their knowledge into practice...", 4.9f
        ),
        Film(
            "Matrix",
            R.drawable.matrix,
            "In the future, the reality that exists for most people is a brain-in-a-flask simulation created by intelligent machines to subjugate and subdue the human population. The heat and electrical activity of their bodies are used by machines as a source of energy.",6.8f
        )
}