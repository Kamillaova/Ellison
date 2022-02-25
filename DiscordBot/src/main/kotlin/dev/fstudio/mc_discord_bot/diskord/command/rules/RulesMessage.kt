package dev.fstudio.mc_discord_bot.diskord.command.rules

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import dev.fstudio.mc_discord_bot.footerText

fun Embed.embedRulesMessage() {
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

    title = "ПУНКТЫ ПРАВИЛ:"
    fields = ruleFields
    color = 16723200
    footer = EmbedFooter(footerText)

}
