package dev.fstudio.mc_discord_bot.diskord.command.roles

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.common.Emoji
import com.jessecorbett.diskord.api.common.formatted
import dev.fstudio.mc_discord_bot.footerText
import dev.fstudio.mc_discord_bot.utils.MicsUtil
import dev.fstudio.mc_discord_bot.utils.MicsUtil.toRoleMention

fun embedRolesMessage(): Embed {
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
    return Embed(
        title =
        "На нашем дискорд-сервере присутствует большое количество ролей, обо всех можно узнать в данном канале.\n",
        description = "```yaml\nРоли с автоматическим получением за общение в каналах сервера:\n```",
        fields = roleFields,
        color = MicsUtil.getRandomColor(),
        footer = EmbedFooter(footerText)
    )
}