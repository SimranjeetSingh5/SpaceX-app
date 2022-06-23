package com.example.spacexapp.listeners

import com.example.spacexapp.models.Rocket

interface RocketListeners {
    fun onItemClicked(rockets: Rocket)
}