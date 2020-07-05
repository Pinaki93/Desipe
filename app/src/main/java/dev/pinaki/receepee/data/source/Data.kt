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

private fun getSteps(): List<String> {
    return ArrayList<String>().apply {
        add("Fry the onions until they turn golden")
        add("Add the dry spices mentioned in the ingredients")
        add("Add chicken to the pan and fry for about 15 minutes")
        add("Keep the preparation aside")
        add("Boil the rice in a separate vessel for 30 minutes")
        add("Now take the vessel away from flame")
        add("Prepare layers of rice and chicken masala stacked on each other.Prepare layers of rice and chicken masala stacked on each other.Prepare layers of rice and chicken masala stacked on each other.Prepare layers of rice and chicken masala stacked on each other")
        add("Now seal the vessel with dough and cook for 20 mins")
        add("Your biriyani is now ready")
    }
}

fun getSampleData(): List<Recipe> {
    return ArrayList<Recipe>().apply {
        add(
            Recipe(
                1,
                "Desi Lamb Tikka Burger",
                "A burger containing a juicy fat patty made of lamb served with tomatoes and lettue",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900066/food-1081707_640_ezrubj.jpg",
                ArrayList<Ingredient>().apply {
                    add(Ingredient("Bun", 2, "Units"))
                    add(Ingredient("Lettuce", 4, "leaves"))
                    add(Ingredient("Onion", 1, "small bowl"))
                    add(Ingredient("Minced Meat", 250, "grams"))
                    add(Ingredient("Turmeric", 1, "tsp"))
                    add(Ingredient("Chilly Powder", 1, "tsp"))
                },
                ArrayList<String>().apply {
                    add("Mix the dry spices, chopped onions with minced meat")
                    add("Make patties from the mixture ")
                    add("Dry the patties until both sides are well cooked")
                    add("Cut tomatoes into circles")
                    add("Take a bun, put lettuce leaves on it")
                    add("Put two of the sliced tomatoes")
                    add("Put the patty on the top of the tomatoes, repeat two layers above it")
                    add("Put another bun on top")
                    add("Your Desi Lamb Tikka burger is now ready!")
                }
            )
        )

        add(
            Recipe(
                2,
                "Chicken Dum Biriyani",
                "A delicacy prepared in different parts of India with tender chicken pieces and aromatic long grain rice",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900068/biryani-1141444_640_przhfd.jpg",
                ArrayList<Ingredient>().apply {
                    add(Ingredient("Rice", 500, "gms"))
                    add(Ingredient("Oil", 4, "tsp"))
                    add(Ingredient("Cinnamon", 20, "gms"))
                    add(Ingredient("Cloves", 6, "pieces"))
                    add(Ingredient("Garlic", 4, "cloves"))
                    add(Ingredient("Chicken", 250, "grams"))
                    add(Ingredient("Onion", 5, "Medium Sized"))
                    add(Ingredient("Wheat Dough", 100, "grams"))
                },
                ArrayList<String>().apply {
                    add("Fry the onions until they turn golden")
                    add("Add the dry spices mentioned in the ingredients")
                    add("Add chicken to the pan and fry for about 15 minutes")
                    add("Keep the preparation aside")
                    add("Boil the rice in a separate vessel for 30 minutes")
                    add("Now take the vessel away from flame")
                    add("Prepare layers of rice and chicken masala stacked on each other.Prepare layers of rice and chicken masala stacked on each other.Prepare layers of rice and chicken masala stacked on each other.Prepare layers of rice and chicken masala stacked on each other")
                    add("Now seal the vessel with dough and cook for 20 mins")
                    add("Your biriyani is now ready")
                }
            )
        )

        add(
            Recipe(
                3,
                "Bhel",
                "An evening snack popular all across India, knows by many different regional names",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900066/jhal-muri-827292_640_btx94u.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                4,
                "Rasgulla",
                "An east Indian delicacy with a controversial origin but that doesn't stop anyone from loving it!",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900066/sweets-577230_640_cekunj.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                5,
                "Omlette",
                "A quick to prepare meal for any time of the day, savoured all over the world",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900066/food-4046229_640_ub6i4e.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                6,
                "Plain Dosa",
                "Choice of drug for sober people all across India hailing from the South.",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900067/indian-1768906_640_j6olcu.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                7,
                "Khaman",
                "A savoury steamed dish from Gujarat that can make it's way to any meal you can ever have.",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900067/indian-1969797_640_qaibyr.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                8,
                "Samosa",
                "Crunchy on the outside, hot and soft on the inside. If Samosa is a question, yes is the answer",
                "https://res.cloudinary.com/pinaki93/image/upload/v1593900067/food-143622_640_zlg7yv.jpg",
                getIngredients(),
                getSteps()
            )
        )
    }
}