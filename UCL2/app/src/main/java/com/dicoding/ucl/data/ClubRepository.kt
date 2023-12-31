package com.dicoding.ucl.data

import com.dicoding.ucl.model.Club
import com.dicoding.ucl.model.ClubData

class ClubRepository {
    fun getClub(): List<Club> {
        return ClubData.club
    }

    fun searchClub(query: String): List<Club> {
        return ClubData.club.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}