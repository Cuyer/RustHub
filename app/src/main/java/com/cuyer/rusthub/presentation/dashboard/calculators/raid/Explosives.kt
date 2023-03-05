package com.cuyer.rusthub.presentation.dashboard.calculators.raid

data class Explosives(
    val image: String = "",
    val amount: Int = 0,
    val cost: List<Cost> = emptyList()
)

// Images
private const val EXPLOSIVE_AMMO_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/3/31/Explosive_5.56_Rifle_Ammo_icon.png"
private const val ROCKET_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/9/95/Rocket_icon.png"
private const val SATCHEL_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/b/ba/Satchel_Charge_icon-0.png"
private const val C4_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/6/6c/Timed_Explosive_Charge_icon.png"
private const val BEANCAN_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/b/be/Beancan_Grenade_icon.png"
private const val MOLOTOV_IMAGE = "https://rustlabs.com/img/items180/grenade.molotov.png"
private const val F1_GRENADE = "https://static.wikia.nocookie.net/play-rust/images/5/52/F1_Grenade_icon.png"
private const val HANDMANDE_SHELL = "https://static.wikia.nocookie.net/play-rust/images/0/0d/Handmade_Shell_icon.png"


fun Explosives.woodenDoor(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = MOLOTOV_IMAGE, amount = 2, cost = Cost().molotov()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 2, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 19, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 6, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = HANDMANDE_SHELL, amount = 45, cost = Cost().handmadeShell()))
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 1, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 1, cost = Cost().c4()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 23, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.metalDoor(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 2, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 1, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 4, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 63, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 18, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 50, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.garageDoor(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 3, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 2, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 9, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 150, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 42, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 120, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.armoredDoor(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 4, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 2, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 12, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 200, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 56, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 160, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.ladderHatch(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 2, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 1, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 4, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 63, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 18, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 50, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.shopFront(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 6, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 3, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 18, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 300, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 99, cost = Cost().beancan()))

    return explosivesList
}

fun Explosives.woodenExternalWall(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 3, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 2, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 6, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 98, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 26, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = HANDMANDE_SHELL, amount = 186, cost = Cost().handmadeShell()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 118, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.stoneExternalWall(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 4, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 2, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 10, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 185, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 46, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = HANDMANDE_SHELL, amount = 556, cost = Cost().handmadeShell()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 182, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.woodenWall(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 2, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 1, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 3, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 49, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 13, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = HANDMANDE_SHELL, amount = 93, cost = Cost().handmadeShell()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 59, cost = Cost().f1()))

    return explosivesList
}

fun Explosives.stoneWall(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 4, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 2, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 10, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 185, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 46, cost = Cost().beancan()))
    explosivesList.add(Explosives(image = HANDMANDE_SHELL, amount = 556, cost = Cost().handmadeShell()))
    explosivesList.add(Explosives(image = F1_GRENADE, amount = 182, cost = Cost().f1()))

    return explosivesList
}
fun Explosives.metalWall(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 8, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 4, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 23, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 400, cost = Cost().explosiveAmmo()))
    explosivesList.add(Explosives(image = BEANCAN_IMAGE, amount = 112, cost = Cost().beancan()))

    return explosivesList
}
fun Explosives.armoredWall(): List<Explosives> {
    val explosivesList = mutableListOf<Explosives>()
    explosivesList.add(Explosives(image = ROCKET_IMAGE, amount = 15, cost = Cost().rocket()))
    explosivesList.add(Explosives(image = C4_IMAGE, amount = 8, cost = Cost().c4()))
    explosivesList.add(Explosives(image = SATCHEL_IMAGE, amount = 46, cost = Cost().satchel()))
    explosivesList.add(Explosives(image = EXPLOSIVE_AMMO_IMAGE, amount = 799, cost = Cost().explosiveAmmo()))

    return explosivesList
}
