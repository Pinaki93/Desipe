package dev.pinaki.receepee.data.source

import dev.pinaki.receepee.data.model.Ingredient
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.data.model.RecipeImage

private fun getIngredients(): List<Ingredient> {
    return ArrayList<Ingredient>().apply {
        add(Ingredient("Rice", 500, "gms"))
        add(Ingredient("Oil", 4, "tsp"))
        add(Ingredient("Cinnamon", 20, "gms"))
        add(Ingredient("Garlic", 4, "cloved"))
        add(Ingredient("Onion", 2, "Medium Sized"))
    }
}

private fun getImage() = "https://cdn.pixabay.com/photo/2016/01/15/10/56/biryani-1141444_1280.jpg"

private fun getSteps(): List<String> {
    return ArrayList<String>().apply {
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
    }
}

fun getSampleData(): List<Recipe> {
    return ArrayList<Recipe>().apply {
        add(
            Recipe(
                1,
                "Palak Paneer",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                2,
                "Chicken Biriyani",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                3,
                "Greek Salad",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                4,
                "Cheese burst Pizza",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                5,
                "Turkey Sandwich",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )
    }
}