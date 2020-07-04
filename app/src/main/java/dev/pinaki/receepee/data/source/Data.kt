package dev.pinaki.receepee.data.source

import dev.pinaki.receepee.data.model.Ingredient
import dev.pinaki.receepee.data.model.Recipe
import dev.pinaki.receepee.data.model.RecipeImage

private fun getIngredients(): List<Ingredient> {
    return ArrayList<Ingredient>().apply {
        add(Ingredient("Rice", 500, "gms"))
        add(Ingredient("Oil", 4, "tsp"))
        add(Ingredient("Cinnamon", 20, "gms"))
        add(Ingredient("Cloves", 6, "pieces"))
        add(Ingredient("Garlic", 4, "cloves"))
        add(Ingredient("Chicken", 250, "grams"))
        add(Ingredient("Onion", 5, "Medium Sized"))
        add(Ingredient("Wheat Dough", 100, "grams"))
    }
}

private fun getImage() = "https://cdn.pixabay.com/photo/2016/01/15/10/56/biryani-1141444_1280.jpg"

private fun getSteps(): List<String> {
    return ArrayList<String>().apply {
        add("Fry the onions until they turn golden")
        add("Add the dry spices mentioned in the ingredients")
        add("Add chicken to the pan and fry for about 15 minutes")
        add("Keep the preparation aside")
        add("Boil the rice in a separate vessel for 30 minutes")
        add("Now take the vessel away from flame")
        add("Prepare layers of rice and chicken masala stacked on each other")
        add("Now seal the vessel with dough and cook for 20 mins")
        add("Your biriyani is now ready")
    }
}

fun getSampleData(): List<Recipe> {
    return ArrayList<Recipe>().apply {
        add(
            Recipe(
                1,
                "Palak Paneer",
                "A delicacy prepared in different parts of India with tender chicken pieces and aromatic long grain rice",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                2,
                "Chicken Biriyani",
                "A delicacy prepared in different parts of India with tender chicken pieces and aromatic long grain rice",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                3,
                "Greek Salad",
                "A delicacy prepared in different parts of India with tender chicken pieces and aromatic long grain rice",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                4,
                "Cheese burst Pizza",
                "A delicacy prepared in different parts of India with tender chicken pieces and aromatic long grain rice",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                5,
                "Turkey Sandwich",
                "A delicacy prepared in different parts of India with tender chicken pieces and aromatic long grain rice",
                getImage(),
                getIngredients(),
                getSteps()
            )
        )
    }
}

/*http://imgbox.com/Vbi7WnSF
http://imgbox.com/87gUpyQx
http://imgbox.com/k8meaLIh
http://imgbox.com/bqlnPcxt
http://imgbox.com/6U4Jibt9
http://imgbox.com/hIo7iOAf
http://imgbox.com/J4Telz5u
http://imgbox.com/bfe0lffD*/