package com.ddmukhin.currencytracker.ui.model

fun <T : Comparable<T>> List<CurrencyItem>.sorted(item: SortItem<T>): List<CurrencyItem> =
    this.sortedWith { a, b ->
        item.subItems.find { it.isSelected }?.type?.comparator?.let { compareTo ->
            compareTo(item.type.fieldMapper(a), item.type.fieldMapper(b))
        } ?: 0
    }


data class SortItem<T : Comparable<T>>(
    val name: String,
    val type: SortType<T>,
    val subItems: List<SortSubItem<T>>
)

data class SortSubItem<in T : Comparable<T>>(
    val name: String,
    val type: SortSubType<T>,
    var isSelected: Boolean = false
)

sealed class SortType<T>(
    val fieldMapper: (CurrencyItem) -> T
) {
    object Alphabet : SortType<String>({ it.name })
    object Value : SortType<Double>({ it.value })
}

sealed class SortSubType<in T : Comparable<T>>(
    val comparator: (T, T) -> Int
) {

    class Up<in T : Comparable<T>> : SortSubType<T>(
        { a, b ->
            a.compareTo(b)
        }
    )

    class Down<in T : Comparable<T>> : SortSubType<T>(
        { a, b ->
            b.compareTo(a)
        }
    )

}