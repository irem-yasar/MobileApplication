package com.example.mobileapps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapps.databinding.ActivityMainBinding
import com.example.mobileapps.databinding.ItemRecipeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample data for the RecyclerView
        val recipes = listOf(
            Recipe(1, "Pasta", "A delicious pasta dish", R.drawable.pasta),
            Recipe(2, "Pizza", "Cheesy and tasty pizza", R.drawable.pizza),
            Recipe(3, "Burger", "Juicy beef burger", R.drawable.hamburger)
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecipeAdapter(recipes, object : RecipeClickListener {
                override fun onItemClicked(recipe: Recipe) {
                    Toast.makeText(this@MainActivity, "Clicked on ${recipe.name}", Toast.LENGTH_SHORT).show()
                }

                override fun onActionClicked(recipe: Recipe, action: String) {
                    Toast.makeText(this@MainActivity, "Action $action on ${recipe.name}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

data class Recipe(val id: Int, val name: String, val description: String, val imageResId: Int)

interface RecipeClickListener {
    fun onItemClicked(recipe: Recipe)
    fun onActionClicked(recipe: Recipe, action: String)
}

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val listener: RecipeClickListener
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe, listener)
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe, listener: RecipeClickListener) {
            binding.recipeImage.setImageResource(recipe.imageResId)
            binding.recipeName.text = recipe.name
            binding.recipeDescription.text = recipe.description

            binding.root.setOnClickListener {
                listener.onItemClicked(recipe)
            }

            binding.likeButton.setOnClickListener {
                listener.onActionClicked(recipe, "Like")
            }

            binding.shareButton.setOnClickListener {
                listener.onActionClicked(recipe, "Share")
            }
        }
    }
}
