package com.carnava.android.app.domain.utils

import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.category.domain.models.CategoryModel

object DatabaseCategoryInit {
    suspend operator fun invoke() {
        val categories = listOf(
            CategoryModel(0, "Верх", image = R.drawable.ic_logo),
            CategoryModel(1, "Футболки", 0),
            CategoryModel(2, "Рубашки", 0),
            CategoryModel(3, "Толстовки", 0),
            CategoryModel(4, "Свитеры", 0),
            CategoryModel(5, "Низ", image = R.drawable.ic_logo),
            CategoryModel(6, "Брюки", 5),
            CategoryModel(7, "Джинсы", 5),
            CategoryModel(8, "Шорты", 5),
            CategoryModel(9, "Верхняя одежда", image = R.drawable.ic_logo),
            CategoryModel(10, "Куртки", 9),
            CategoryModel(11, "Пальто", 9),
            CategoryModel(12, "Пиджаки", 9),
            CategoryModel(13, "Обувь", image = R.drawable.ic_logo),
            CategoryModel(14, "Сандалии", 13),
            CategoryModel(15, "Туфли", 13),
            CategoryModel(16, "Кроссовки", 13),
            CategoryModel(17, "Кеды", 13),
            CategoryModel(18, "Акссесуары", image = R.drawable.ic_logo),
            CategoryModel(19, "Головные уборы", 18),
            CategoryModel(20, "Очки", 18),
            CategoryModel(21, "Рюкзаки", 18),
            CategoryModel(22, "Ремни", 18),
        )

        categories.forEach { App.categoryRepository.saveCategory(it) }
    }
}