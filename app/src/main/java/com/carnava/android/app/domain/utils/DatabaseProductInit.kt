package com.carnava.android.app.domain.utils

import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.product.domain.models.ProductModel

object DatabaseProductInit {
    suspend operator fun invoke() {
        val products = listOf(
            ProductModel(
                identification = 0,
                idCategory = 1,
                title = "Футболка",
                image = R.drawable.img_111,
                price = 1500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 1,
                idCategory = 1,
                title = "Футболка",
                image = R.drawable.img_112,
                price = 1500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 2,
                idCategory = 1,
                title = "Футболка",
                image = R.drawable.img_113,
                price = 1000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 3,
                idCategory = 2,
                title = "Рубашка",
                image = R.drawable.img_121,
                price = 1000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 4,
                idCategory = 2,
                title = "Рубашка",
                image = R.drawable.img_122,
                price = 2000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 5,
                idCategory = 2,
                title = "Рубашка",
                image = R.drawable.img_123,
                price = 1500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 6,
                idCategory = 3,
                title = "Толстовка",
                image = R.drawable.img_131,
                price = 1500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 7,
                idCategory = 3,
                title = "Толстовка",
                image = R.drawable.img_132,
                price = 2000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 8,
                idCategory = 3,
                title = "Толстовка",
                image = R.drawable.img_133,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 9,
                idCategory = 4,
                title = "Свитер",
                image = R.drawable.img_141,
                price = 2200,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 10,
                idCategory = 4,
                title = "Свитер",
                image = R.drawable.img_142,
                price = 2300,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 11,
                idCategory = 4,
                title = "Свитер",
                image = R.drawable.img_143,
                price = 1500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 10,
                idCategory = 6,
                title = "Брюки",
                image = R.drawable.img_211,
                price = 2000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 11,
                idCategory = 6,
                title = "Брюки",
                image = R.drawable.img_212,
                price = 2300,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 12,
                idCategory = 6,
                title = "Брюки",
                image = R.drawable.img_213,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 13,
                idCategory = 6,
                title = "Брюки",
                image = R.drawable.img_214,
                price = 1900,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 14,
                idCategory = 7,
                title = "Джинсы",
                image = R.drawable.img_221,
                price = 2100,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 15,
                idCategory = 7,
                title = "Джинсы",
                image = R.drawable.img_222,
                price = 1800,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 16,
                idCategory = 7,
                title = "Джинсы",
                image = R.drawable.img_223,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 17,
                idCategory = 8,
                title = "Шорты",
                image = R.drawable.img_231,
                price = 1900,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 1,
                idCategory = 8,
                title = "Шорты",
                image = R.drawable.img_232,
                price = 1800,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 19,
                idCategory = 8,
                title = "Шорты",
                image = R.drawable.img_233,
                price = 2100,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 20,
                idCategory = 10,
                title = "Куртка",
                image = R.drawable.img_311,
                price = 4500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 21,
                idCategory = 10,
                title = "Куртка",
                image = R.drawable.img_312,
                price = 4000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 22,
                idCategory = 10,
                title = "Куртка",
                image = R.drawable.img_313,
                price = 3500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 23,
                idCategory = 11,
                title = "Пальто",
                image = R.drawable.img_321,
                price = 4500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 24,
                idCategory = 11,
                title = "Пальто",
                image = R.drawable.img_322,
                price = 5500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 25,
                idCategory = 11,
                title = "Пальто",
                image = R.drawable.img_323,
                price = 6500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 26,
                idCategory = 12,
                title = "Пиджак",
                image = R.drawable.img_331,
                price = 4000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 27,
                idCategory = 12,
                title = "Пиджак",
                image = R.drawable.img_332,
                price = 5500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 28,
                idCategory = 12,
                title = "Пиджак",
                image = R.drawable.img_333,
                price = 4400,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 29,
                idCategory = 14,
                title = "Сандалии",
                image = R.drawable.img_411,
                price = 3600,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 30,
                idCategory = 14,
                title = "Сандалии",
                image = R.drawable.img_412,
                price = 2400,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 31,
                idCategory = 14,
                title = "Сандалии",
                image = R.drawable.img_413,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 32,
                idCategory = 15,
                title = "Туфли",
                image = R.drawable.img_421,
                price = 6000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 33,
                idCategory = 15,
                title = "Туфли",
                image = R.drawable.img_422,
                price = 5000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 34,
                idCategory = 15,
                title = "Туфли",
                image = R.drawable.img_423,
                price = 4000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 35,
                idCategory = 16,
                title = "Кроссовки",
                image = R.drawable.img_431,
                price = 3500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 36,
                idCategory = 16,
                title = "Кроссовки",
                image = R.drawable.img_432,
                price = 4200,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 37,
                idCategory = 16,
                title = "Кроссовки",
                image = R.drawable.img_433,
                price = 2600,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 38,
                idCategory = 17,
                title = "Кеды",
                image = R.drawable.img_441,
                price = 2200,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 39,
                idCategory = 17,
                title = "Кеды",
                image = R.drawable.img_442,
                price = 1900,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 40,
                idCategory = 17,
                title = "Кеды",
                image = R.drawable.img_443,
                price = 1800,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 41,
                idCategory = 19,
                title = "Шапка",
                image = R.drawable.img_511,
                price = 2000,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 42,
                idCategory = 19,
                title = "Кепка",
                image = R.drawable.img_512,
                price = 800,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 43,
                idCategory = 19,
                title = "Шапка",
                image = R.drawable.img_513,
                price = 1300,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 44,
                idCategory = 19,
                title = "Кепка",
                image = R.drawable.img_514,
                price = 700,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 45,
                idCategory = 2300,
                title = "Очки",
                image = R.drawable.img_521,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 46,
                idCategory = 3600,
                title = "Очки",
                image = R.drawable.img_522,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 47,
                idCategory = 1400,
                title = "Очки",
                image = R.drawable.img_523,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 48,
                idCategory = 1900,
                title = "Очки",
                image = R.drawable.img_524,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 49,
                idCategory = 21,
                title = "Рюкзак",
                image = R.drawable.img_531,
                price = 2200,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 50,
                idCategory = 21,
                title = "Рюкзак",
                image = R.drawable.img_532,
                price = 2800,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 51,
                idCategory = 21,
                title = "Рюкзак",
                image = R.drawable.img_533,
                price = 2500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 52,
                idCategory = 22,
                title = "Ремень",
                image = R.drawable.img_541,
                price = 1500,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 53,
                idCategory = 22,
                title = "Ремень",
                image = R.drawable.img_542,
                price = 1400,
                isCart = false,
                isFavorite = false
            ),

            ProductModel(
                identification = 54,
                idCategory = 22,
                title = "Ремень",
                image = R.drawable.img_543,
                price = 1200,
                isCart = false,
                isFavorite = false
            ),
        )
        App.productRepository.addProducts(products)
    }
}