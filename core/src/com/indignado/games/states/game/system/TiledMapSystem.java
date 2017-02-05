package com.indignado.games.states.game.system;

import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.entitas.api.IContext;
import com.ilargia.games.entitas.collector.Collector;
import com.ilargia.games.entitas.systems.ReactiveSystem;
import com.indignado.games.states.game.component.Tiled;
import com.indignado.games.states.game.gen.Entitas;
import com.indignado.games.states.game.gen.SceneContext;
import com.indignado.games.states.game.gen.SceneEntity;
import com.indignado.games.states.game.gen.SceneMatcher;

import java.util.List;


public class TiledMapSystem extends ReactiveSystem<SceneEntity> {

    private EGAssetsManager assetsManager;
    private SceneContext context;

    public TiledMapSystem(Entitas entitas, EGAssetsManager assetsManager) {
        super(entitas.scene);
        this.context = entitas.scene;
        this.assetsManager = assetsManager;
    }

    @Override
    protected Collector<SceneEntity> getTrigger(IContext<SceneEntity> context) {
        return context.createCollector(SceneMatcher.Tiled());
    }

    @Override
    protected boolean filter(SceneEntity entity) {
        return entity.hasTiled();
    }

    @Override
    protected void execute(List<SceneEntity> gameEntities) {
        Tiled tiled = context.getTiled();
        assetsManager.getMap(tiled.tileMapName);
    }

}
