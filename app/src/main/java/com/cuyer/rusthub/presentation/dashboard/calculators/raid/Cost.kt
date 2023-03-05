package com.cuyer.rusthub.presentation.dashboard.calculators.raid

data class Cost(
    val image: String = "",
    val amount: Int = 0
)

private const val SULFUR_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/3/32/Sulfur_icon.png"
private const val GUNPOWDER_IMAGE = "https://rustlabs.com/img/items180/gunpowder.png"
private const val EXPLOSIVES_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/4/47/Explosives_icon.png"
private const val LOWGRADE_IMAGE = "https://rustlabs.com/img/items180/lowgradefuel.png"
private const val CLOTH_IMAGE = "https://rustlabs.com/img/items180/cloth.png"
private const val PIPES_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/4/4a/Metal_Pipe_icon.png"
private const val TECH_TRASH_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/e/eb/Tech_Trash_icon.png"
private const val SMALL_STASH_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/5/53/Small_Stash_icon.png"
private const val ROPES_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/1/15/Rope_icon.png"
private const val BEANCAN_IMAGE_COST = "https://static.wikia.nocookie.net/play-rust/images/b/be/Beancan_Grenade_icon.png"
private const val METAL_FRAGS = "https://static.wikia.nocookie.net/play-rust/images/7/74/Metal_Fragments_icon.png"
private const val STONES = "https://rustlabs.com/img/items180/stones.png"


fun Cost.explosiveAmmo(): List<Cost> {
    val explosiveAmmoList = mutableListOf<Cost>()
    explosiveAmmoList.add(Cost(image = METAL_FRAGS, amount = 10))
    explosiveAmmoList.add(Cost(image = GUNPOWDER_IMAGE, amount = 20))
    explosiveAmmoList.add(Cost(image = SULFUR_IMAGE, amount = 10))
    return explosiveAmmoList
}

fun Cost.rocket(): List<Cost> {
    val rocketList = mutableListOf<Cost>()
    rocketList.add(Cost(image = PIPES_IMAGE, amount = 2))
    rocketList.add(Cost(image = GUNPOWDER_IMAGE, amount = 150))
    rocketList.add(Cost(image = EXPLOSIVES_IMAGE, amount = 10))
    return rocketList
}

fun Cost.satchel(): List<Cost> {
    val satchelList = mutableListOf<Cost>()
    satchelList.add(Cost(image = BEANCAN_IMAGE_COST, amount = 4))
    satchelList.add(Cost(image = SMALL_STASH_IMAGE, amount = 1))
    satchelList.add(Cost(image = ROPES_IMAGE, amount = 1))
    return satchelList
}

fun Cost.c4(): List<Cost> {
    val c4List = mutableListOf<Cost>()
    c4List.add(Cost(image = EXPLOSIVES_IMAGE, amount = 20))
    c4List.add(Cost(image = CLOTH_IMAGE, amount = 5))
    c4List.add(Cost(image = TECH_TRASH_IMAGE, amount = 2))
    return c4List
}

fun Cost.beancan(): List<Cost> {
    val beancanList = mutableListOf<Cost>()
    beancanList.add(Cost(image = GUNPOWDER_IMAGE, amount = 60))
    beancanList.add(Cost(image = METAL_FRAGS, amount = 20))
    return beancanList
}

fun Cost.molotov(): List<Cost> {
    val molotovList = mutableListOf<Cost>()
    molotovList.add(Cost(image = CLOTH_IMAGE, amount = 10))
    molotovList.add(Cost(image = LOWGRADE_IMAGE, amount = 50))
    return molotovList
}

fun Cost.f1(): List<Cost> {
    val f1List = mutableListOf<Cost>()
    f1List.add(Cost(image = METAL_FRAGS, amount = 25))
    f1List.add(Cost(image = GUNPOWDER_IMAGE, amount = 30))
    return f1List
}

fun Cost.handmadeShell(): List<Cost> {
    val handmadeShellList = mutableListOf<Cost>()
    handmadeShellList.add(Cost(image = STONES, amount = 2))
    handmadeShellList.add(Cost(image = GUNPOWDER_IMAGE, amount = 2))
    return handmadeShellList
}
