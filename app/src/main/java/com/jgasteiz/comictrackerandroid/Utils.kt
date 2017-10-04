package com.jgasteiz.comictrackerandroid

import com.jgasteiz.comictrackerandroid.models.Comic

class Utils {

    fun getSampleWeeklyReleases (): List<Comic> {
        return listOf(
            Comic(
                    8053558,
                    "Batman #32",
                    "DC Comics",
                    "2017-10-04",
                    "$2.99",
                    "“THE WAR OF JOKES AND RIDDLES” finale! This is the finale everyone will be talking about for years to come! In BATMAN #24, we gave you the question; in BATMAN #32, you get the answer. As the Riddler and The Joker... ",
                    "http://leagueofcomicgeeks.com/comics/covers/large/8053558-batman-32.jpg?1506995022",
                    0,
                    "https://leagueofcomicgeeks.com/comic/8053558/batman-32",
                    true
            ),
            Comic(
                    5513337,
                    "Star Wars #37",
                    "Marvel Comics",
                    "2017-10-04",
                    "$4.99",
                    "SCAR Squadron is back... with their deadliest attack yet! ",
                    "http://leagueofcomicgeeks.com/comics/covers/large/5513337-star-wars-37.jpg?1506996200",
                    1,
                    "https://leagueofcomicgeeks.com/comic/5513337/star-wars-37",
                    false
            ),
            Comic(
                    3819472,
                    "All-Star Batman #14",
                    "DC Comics",
                    "2017-10-04",
                    "$4.99",
                    "“The First Ally” finale! In the final issue of Scott Snyder’s high-octane run on ALL STAR BATMAN, he and superstar artist Rafael Albuquerque put Batman to the ultimate test! Faced with either losing his... ",
                    "http://leagueofcomicgeeks.com/comics/covers/large/3819472-all-star-batman-14.jpg?1506995022",
                    2,
                    "https://leagueofcomicgeeks.com/comic/3819472/all-star-batman-14",
                    true
            ),
            Comic(
                    9087022,
                    "The Walking Dead #172",
                    "Image Comics",
                    "2017-10-04",
                    "$2.99",
                    "HILLTOP REBORN - The Hilltop is rebuilt as other parts of the world are torn down.",
                    "http://leagueofcomicgeeks.com/comics/covers/large/9087022-the-walking-dead-172.jpg?1507072596",
                    3,
                    "https://leagueofcomicgeeks.com/comic/9087022/the-walking-dead-172",
                    true
            ),
            Comic(
                    4057293,
                    "Superman #32",
                    "DC Comics",
                    "2017-10-04",
                    "$2.99",
                    "“BREAKING POINT” Part 2 Deathstroke has come to Metropolis… and he’s got his sights set on Superman. Slade Wilson gives the Man of Steel an impossible choice: maintain his ideals and let the love... ",
                    "http://leagueofcomicgeeks.com/comics/covers/large/4057293-superman-32.jpg?1506995022",
                    4,
                    "https://leagueofcomicgeeks.com/comic/4057293/superman-32",
                    false
            )
        )
    }
}