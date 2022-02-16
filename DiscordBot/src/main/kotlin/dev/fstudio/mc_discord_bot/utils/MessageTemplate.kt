package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.channel.EmbedImage
import com.jessecorbett.diskord.api.common.Emoji
import com.jessecorbett.diskord.api.common.formatted
import dev.fstudio.mc_discord_bot.api.mcapi.ping.ServerPing
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApi
import dev.fstudio.mc_discord_bot.api.mcworldstats.Player
import dev.fstudio.mc_discord_bot.api.mcworldstats.Stats
import dev.fstudio.mc_discord_bot.utils.DiskordBotManager.LOGGER
import dev.fstudio.mc_discord_bot.utils.MicsUtil.convertToDead
import dev.fstudio.mc_discord_bot.utils.MicsUtil.fixUnderline
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getAllBlocks
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getGroundWalkedDistance
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getRandomColor
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getSwamDistance
import dev.fstudio.mc_discord_bot.utils.MicsUtil.tickToTime
import dev.fstudio.mc_discord_bot.utils.MicsUtil.toRoleMention
import dev.fstudio.mc_discord_bot.utils.MicsUtil.withSuffix

object MessageTemplate {

    suspend fun onlinePlayers(data: ServerPing, mcStats: MCWorldApi): Embed.() -> Unit {

        val playersList = mutableListOf<EmbedField>()

        data.players.sample?.forEach { player ->
            kotlin.runCatching {
                mcStats.getStats(player.name)
            }.onSuccess {
                playersList.add(
                    EmbedField(
                        player.name.fixUnderline(),
                        "$realDaysInDay ${it.minecraftPlayOneMinute.tickToTime()}",
                        true
                    )
                )
            }.onFailure {
                LOGGER.error(it.stackTraceToString())
            }
        }

        return {
            title = onlinePlayersTitle
            description = "$modsCountOnServer ${data.forgeData?.mods?.size}\n" +
                    "$serverVersion ${data.version?.name}\n\n"
            fields = playersList
            color = getRandomColor()
            footer = EmbedFooter("$playersCount ${data.players?.online} / ${data.players?.max}\n" + footerText)
            thumbnail = EmbedImage(data.favicon)
        }
    }

    fun allPlayers(data: List<Player>): Embed.() -> Unit = {
        title = allPlayersTitle
        description = StringBuilder().apply {
            data.forEachIndexed { index, player ->
                append("**${index + 1}. **${player.name.fixUnderline().convertToDead(player.abandoned)}\n")
            }
        }.toString()
        color = getRandomColor()
        footer = EmbedFooter(footerText)
    }

    fun topPlayers(data: List<Player>): Embed.() -> Unit {

        val topTen = mutableListOf<EmbedField>()

        for (i in 0..9) {
            topTen.add(
                EmbedField(
                    "${i + 1}. ${data[i].name.fixUnderline().convertToDead(data[i].abandoned)}",
                    "$realDaysInDay ${data[i].minecraftPlayOneMinute?.tickToTime()}",
                    false
                )
            )
        }

        return {
            title = topPlayersTitle
            fields = topTen
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }
    }

    fun playerStats(username: String, data: Stats): Embed.() -> Unit = {
        title = "$playerStatsTitle - $username"
        description = statsDescription(data)
        color = getRandomColor()
        footer = EmbedFooter(footerText)
    }

    private fun statsDescription(data: Stats): String = "**$numberOfActions**\n\n" +
            "**$realDaysInDay **${data.minecraftPlayOneMinute.tickToTime()}\n" +
            "**$killsAndDeath **${data.minecraftPlayerKills.withSuffix()} / ${data.minecraftDeaths.withSuffix()}\n" +
            "**$enchantedItems **${data.minecraftEnchantItem.withSuffix()}\n" +
            "**$droppedItems **${data.minecraftDrop.withSuffix()}\n" +
            "**$killedMobs **${data.minecraftMobKills.withSuffix()}\n" +
            "**$jumpCount **${data.minecraftJump.withSuffix()}\n\n" +
            "**$walkedDistance **${getGroundWalkedDistance(data).withSuffix()}\n" +
            "**$swamDistance **${getSwamDistance(data).withSuffix()}\n" +
            "**$flownDistance **${data.minecraftFlyOneCm.withSuffix()}\n\n" +
            "**$totalDistance **${getAllBlocks(data).withSuffix()}"

    fun roles(): Embed.() -> Unit {

        val roleFields = mutableListOf(
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "832716052172505121",
                        "Wood"
                    ).formatted
                } ${832634001348886599.toRoleMention()} [1 уровень]**",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "832715602812469289",
                        "Stone"
                    ).formatted
                } ${832634113562378241.toRoleMention()} [5 уровень]**",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "832715582734467073",
                        "Iron"
                    ).formatted
                } ${832634161738023012.toRoleMention()} [10 уровень]**",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "917515514044092476",
                        "Emerald"
                    ).formatted
                } ${917499609641803876.toRoleMention()} [15 уровень]**\n" +
                        "*Доступ к смене ника.*",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "832715593735471144",
                        "Diamond"
                    ).formatted
                } ${832634635841568778.toRoleMention()} [20 уровень]**\n" +
                        "*Доступ к созданию публичных веток.*",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "832715629983170601",
                        "Netherite"
                    ).formatted
                } ${832634437124358155.toRoleMention()} [25 уровень]**\n" +
                        "*Позволяет менять цвет своего ника раз в месяц.*\n" +
                        "*Команда !Цвет \"Свой никнейм\" \"код цвета\".*\n" +
                        "*Сайт подбора цвета https://htmlcolorcodes.com/*",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "872141159374413834",
                        "Nano"
                    ).formatted
                } ${855390107199733760.toRoleMention()} [30 уровень]**",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "872141025370570772",
                        "Quant"
                    ).formatted
                } ${855390447404974100.toRoleMention()} [35 уровень]**",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "917528989906636840",
                        "Wyvern"
                    ).formatted
                } ${917529875336806540.toRoleMention()} [40 уровень]**",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "917529426760175668",
                        "Draconic"
                    ).formatted
                } ${917529997814669393.toRoleMention()} [45 уровень]**",
                false
            ),
            EmbedField(
                "ㅤ",
                "**Узнать свой уровень можно командой \"!Ранг\"**\n" +
                        "**Посмотреть таблицу лидеров можно командой \"!Лидеры\"**\n" +
                        "**Выход с сервера обнулит ваш уровень.**\n\n" +
                        "```yaml\nРоли, которые выдаются в ручную:\n```",
                false
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "941425853097705553",
                        "Golden"
                    ).formatted
                } ${875702217481543740.toRoleMention()} [Выдается при крупном пожертвовании на стриме]**\n" +
                        "*Доступ к отдельному текстовому и голосовому каналу*",
                true
            ),
            EmbedField(
                "ㅤ",
                "> **${
                    Emoji(
                        "924039262708572161",
                        "totem"
                    ).formatted
                } ${824634309632851978.toRoleMention()} [Выдается при пожертвовании на стриме или бусте дискорд сервера]**\n" +
                        "> *Доступ к отдельному текстовому и голосовому каналу*",
                true
            ),
            EmbedField(
                "ㅤ",
                "> **:cookie: ${838133081894158348.toRoleMention()} [Выдается при бусте сервера]**",
                true
            ),
            EmbedField(
                "ㅤ",
                "**${
                    Emoji(
                        "927607033971236984",
                        "girl_role"
                    ).formatted
                } ${927588050593259540.toRoleMention()} [Выдается любым участницам сервера]**\n" +
                        "*Доступ к отдельному текстовому и голосовому каналу*",
                true
            ),
            EmbedField(
                "ㅤ",
                "> **:video_game: ${875713168087851019.toRoleMention()} [Возможно получить при наборе]**\n" +
                        "> *Доступ к серверу Prototype*",
                true
            ),
            EmbedField(
                "ㅤ",
                "> **:crossed_swords: ${814337604623007775.toRoleMention()} [Возможно получить при наборе]**",
                true
            ),
            EmbedField(
                "ㅤ",
                "**Чтобы пройти проверку на ${
                    Emoji(
                        "927607033971236984",
                        "girl_role"
                    ).formatted
                }${927588050593259540.toRoleMention()} обращайтесь в лс администрации.**\n\n" +
                        "```yaml\nКастомные роли:\n```\n" +
                        "**1 участник, который достигнет ${
                            Emoji(
                                "872141025370570772",
                                "Quant"
                            ).formatted
                        }${855390447404974100.toRoleMention()} - Получит кастомную роль.**\n" +
                        "**3 участника, которые первыми достигнут ${
                            Emoji(
                                "917529426760175668",
                                "Draconic"
                            ).formatted
                        }${917529997814669393.toRoleMention()} - получат в подарок кастомную роль.**\n" +
                        "*(Не учитывая тех, кто уже получил кастомную роль)*\nㅤ",
                false
            ),
        )
        return {
            title =
                "На нашем дискорд-сервере присутствует большое количество ролей, обо всех можно узнать в данном канале.\n"
            description = "```yaml\nРоли с автоматическим получением за общение в каналах сервера:\n```"
            fields = roleFields
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }

    }

    fun rules(): Embed.() -> Unit {

        val ruleFields = mutableListOf(
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 1. Запрещены грубые провокации и оскорбления пользователей сервера",
                false
            ),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 2. Запрещена публикация материалов грубого, насильственного характера, жестокости, призывы к таковым, сообщения экстремистского толка",
                false
            ),
            EmbedField("ㅤ", ":small_orange_diamond: 3. Запрещен спам, флуд, мошенничество", false),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 4. Запрещена не честная прокачка уровня, в том числе стороннее ПО",
                false
            ),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 5. Запрещено разжигать национальную, религиозную и расовую вражду",
                false
            ),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 6. Запрещена реклама без согласования с администрацией чата",
                false
            ),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 7. Запрещены \"Краш-гифки\" и любые другие материалы вредоносного характера",
                false
            ),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 8. Запрещены упоминания администрации и модерации без весомой причины",
                false
            ),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 9. Запрещена публичная критика и оспаривание действий администрации",
                false
            ),
            EmbedField(
                "ㅤ",
                ":small_orange_diamond: 10. Запрещен грубый оффтоп (Сообщения не по теме канала)",
                false
            ),
            EmbedField(
                "ㅤ",
                "**__Правила являются обязательными для всех участников сервера!__**\n\n**ЗАМЕТИВ НАРУШЕНИЕ, ВЫ МОЖЕТЕ ПОМОЧЬ ЕГО ПРЕСЕЧЬ!**\nКоманда для жалобы на нарушителя: **!Репорт Ник Причина Скриншот**\nㅤ",
                false
            ),
        )

        return {
            title = "ПУНКТЫ ПРАВИЛ:"
            fields = ruleFields
            color = 16723200
            footer = EmbedFooter(footerText)
        }
    }

    fun exception(): Embed.() -> Unit {
        return {
            title = "Я ушла в запой. Вернусь через недельки две"
            image = EmbedImage(
                "https://www.blackpantera.ru/articles/wp-content/uploads/2020/01/devushka-bokal-vino-1024x751.jpg"
            )
            footer = EmbedFooter(footerText)
        }
    }
}