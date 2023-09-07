package earth.health.router

enum class Destination(val link: String) {
    HOME("/"),
    MEALS("/meals"),
    CURRENT_MEAL("/current_meal"),
    WEIGHT("/weight"),
    FOODS("/foods"),
    ADD_FOOD_SCREEN("/add_food_screen"),
    EXPORT_DATA("/export_data");
}
