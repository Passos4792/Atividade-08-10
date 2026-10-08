package br.com.elo.configuracao;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
// -------------------------------------------------------------------------------------
@Configuration
@EnableMongoHttpSession(maxInactiveIntervalInSeconds = 1800, collectionName = "sessoes")
public class SessaoConfiguracao { }
