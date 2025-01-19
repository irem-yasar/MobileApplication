package com.example.mobileapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList()
)

class RecipesViewModel(
    private val credentialsManager: CredentialsManager = App.credentialsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun fetchRecipes(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            delay(2000) // Simulate network delay
            val allRecipes = listOf(
                Recipe(1, "Pasta", "A delicious pasta dish", R.drawable.pasta),
                Recipe(2, "Pizza", "Cheesy and tasty pizza", R.drawable.pizza),
                Recipe(3, "Burger", "Juicy beef burger", R.drawable.hamburger)
            )
            val filteredRecipes = if (query.isEmpty()) {
                allRecipes
            } else {
                allRecipes.filter { it.name.contains(query, ignoreCase = true) }
            }
            _uiState.value = UiState(isLoading = false, recipes = filteredRecipes)
        }
    }

    fun logout() {
        credentialsManager.logout() // Ensure logout flow triggers
    }
}
