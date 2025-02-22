/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.pinaki.desipe

import dev.pinaki.desipe.data.model.Ingredient
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.helper.moshi.DesipeMoshiHelper

private fun getIngredients(): List<Ingredient> {
    return ArrayList<Ingredient>().apply {
        add(Ingredient("Rice", 500, "gms"))
        add(Ingredient("Oil", 4, "tsp"))
        add(Ingredient("Cinnamon", 20, "gms"))
        add(Ingredient("Cloves", 6, "pieces"))
        add(Ingredient("Garlic", 4, "cloves"))
        add(Ingredient("Onion", 5, "Medium Sized"))
        add(Ingredient("Wheat Dough", 100, "grams"))
    }
}

private fun getSteps(): List<String> {
    return ArrayList<String>().apply {
        add("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum")
        add("Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore")
        add("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum")
        add("Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore")
        add("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum")
        add("Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore")
        add("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum")
        add("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum")
        add("Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore")
    }
}

fun getSampleData(): List<Recipe> {
    return ArrayList<Recipe>().apply {
        add(
            Recipe(
                1,
                "Desi Lamb Tikka Burger",
                "A burger containing a juicy fat patty made of lamb served with tomatoes and lettue",
                "img/burger.jpg",
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
                "img/biriyani.jpg",
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
                "img/bhel.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                4,
                "Rasgulla",
                "An east Indian delicacy with a controversial origin but that doesn't stop anyone from loving it!",
                "img/rasgulla.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                5,
                "Omlette",
                "A quick to prepare meal for any time of the day, savoured all over the world",
                "img/omlette.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                6,
                "Plain Dosa",
                "Choice of drug for sober people all across India hailing from the South.",
                "img/dosa.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                7,
                "Khaman",
                "A savoury steamed dish from Gujarat that can make it's way to any meal you can ever have.",
                "img/khaman.jpg",
                getIngredients(),
                getSteps()
            )
        )

        add(
            Recipe(
                8,
                "Samosa",
                "Crunchy on the outside, hot and soft on the inside. If Samosa is a question, yes is the answer",
                "img/samosa.jpg",
                getIngredients(),
                getSteps()
            )
        )
    }
}

fun main() {
    println(DesipeMoshiHelper.recipeListAdapter.toJson(getSampleData()))
}