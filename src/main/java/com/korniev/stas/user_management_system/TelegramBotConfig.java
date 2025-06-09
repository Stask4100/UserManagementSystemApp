package com.korniev.stas.user_management_system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    private final TelegramBot TelegramBot;

    public TelegramBotConfig(TelegramBot TelegramBot) {
        this.TelegramBot = TelegramBot;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(TelegramBot);
        return botsApi;
    }
}
