package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

    @Configuration
    public class SpringModuleConfiguration {

    @Bean
    public Game game() {
        return new Game(entityPlugins(), entityProcessors(), entityPostProcessors()); }

    @Bean
    public List<IGamePluginService> entityPlugins() {
        return ServiceLoader.load(IGamePluginService.class)
                .stream().map(ServiceLoader.Provider::get).collect(Collectors.toList()); }

    @Bean
    public List<IEntityProcessingService> entityProcessors() {
        return ServiceLoader.load(IEntityProcessingService.class)
                .stream().map(ServiceLoader.Provider::get).collect(Collectors.toList()); }

    @Bean
    public List<IPostEntityProcessingService> entityPostProcessors() {
        return ServiceLoader.load(IPostEntityProcessingService.class)
                .stream().map(ServiceLoader.Provider::get).collect(Collectors.toList()); }}