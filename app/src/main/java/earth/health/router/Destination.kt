package earth.health.router

enum class Destination(val link: String) {
    HOME("/"),
    MEALS("/meals"),
    CURRENT_MEAL("/current_meal"),
    WEIGHT("/weight"),
}
