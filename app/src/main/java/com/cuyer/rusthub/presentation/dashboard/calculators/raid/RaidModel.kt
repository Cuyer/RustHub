package com.cuyer.rusthub.presentation.dashboard.calculators.raid

import com.cuyer.rusthub.data.remote.dto.items.Ingredient

data class RaidModel(
    val image: String = "",
    val type: String = "",
    val explosives: List<Explosives> = emptyList()
)

const val WOODEN_DOOR_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/7/7e/Wooden_Door_icon.png"
const val METAL_DOOR_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/8/83/Sheet_Metal_Door_icon.png"
const val GARAGE_DOOR_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/e/e3/Garage_Door_icon.png"
const val ARMORED_DOOR_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/b/bc/Armored_Door_icon.png"
const val LADDER_HATCH_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/7/7c/Ladder_Hatch_icon.png"
const val SHOP_FRONT_IMAGE = "https://static.wikia.nocookie.net/play-rust/images/4/46/Metal_Shop_Front_icon.png"
const val WOODEN_EXTERNAL_WALL = "https://static.wikia.nocookie.net/play-rust/images/9/96/High_External_Wooden_Wall_icon.png"
const val STONE_EXTERNAL_WALL = "https://static.wikia.nocookie.net/play-rust/images/b/b6/High_External_Stone_Wall_icon.png"
const val WOODEN_WALL = "https://static.wikia.nocookie.net/play-rust/images/4/42/Wood_Tier_Icon.png"
const val STONE_WALL = "https://static.wikia.nocookie.net/play-rust/images/8/84/Stone_Tier_Icon.png"
const val METAL_WALL = "https://static.wikia.nocookie.net/play-rust/images/4/47/Sheet_Metal_Tier_Icon.png"
const val ARMORED_WALL = "https://static.wikia.nocookie.net/play-rust/images/f/fa/Armored_Tier_Icon.png"


fun RaidModel.create(): List<RaidModel> {
    val raidModelList = mutableListOf<RaidModel>()
    raidModelList.add(RaidModel(image = WOODEN_DOOR_IMAGE, type = "Door", explosives = Explosives().woodenDoor()))
    raidModelList.add(RaidModel(image = METAL_DOOR_IMAGE, type = "Door", explosives = Explosives().metalDoor()))
    raidModelList.add(RaidModel(image = GARAGE_DOOR_IMAGE, type = "Door", explosives = Explosives().garageDoor()))
    raidModelList.add(RaidModel(image = ARMORED_DOOR_IMAGE, type = "Door", explosives = Explosives().armoredDoor()))
    raidModelList.add(RaidModel(image = LADDER_HATCH_IMAGE, type = "Door", explosives = Explosives().ladderHatch()))
    raidModelList.add(RaidModel(image = SHOP_FRONT_IMAGE, type = "Wall", explosives = Explosives().shopFront()))
    raidModelList.add(RaidModel(image = WOODEN_EXTERNAL_WALL, type = "Wall", explosives = Explosives().woodenExternalWall()))
    raidModelList.add(RaidModel(image = STONE_EXTERNAL_WALL, type = "Wall", explosives = Explosives().stoneExternalWall()))
    raidModelList.add(RaidModel(image = WOODEN_WALL, type = "Wall", explosives = Explosives().woodenWall()))
    raidModelList.add(RaidModel(image = STONE_WALL, type = "Wall", explosives = Explosives().stoneWall()))
    raidModelList.add(RaidModel(image = METAL_WALL, type = "Wall", explosives = Explosives().metalWall()))
    raidModelList.add(RaidModel(image = ARMORED_WALL, type = "Wall", explosives = Explosives().armoredWall()))
    return raidModelList
}
