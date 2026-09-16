package data.scripts.starsystems;

import TERRAN.data.campaign.ids.TerranIDS;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin.MagneticFieldParams;

import java.awt.Color;

public class Starbase4 {

    public void generate(SectorAPI sector) {

        StarSystemAPI system = Global.getSector().createStarSystem("Malachite 61");

        system.getLocation().set(26440f, 2200f);
        system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");

        SectorEntityToken star = system.initStar(
                "starbase4_star",
                "star_yellow",
                700f,
                500f
        );

        system.setLightColor(new Color(255, 245, 220));

        // Main Spacedock
        SectorEntityToken spacedock = system.addCustomEntity(
                "starbase4_spacedock",
                "61 Cygni Spacedock",
                "spacedock_type",
                TerranIDS.TERRAN
        );
        spacedock.setCircularOrbit(star, 180f, 3200f, 180f);

        // Jump Point & Gate
        JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("starbase4_jump", "Spacedock Jump Point");
        jumpPoint.setCircularOrbit(spacedock, 0f, 1250f, 300f);
        system.addEntity(jumpPoint);

        SectorEntityToken gate = system.addCustomEntity(
                "starbase4_gate",
                "Inactive Gate",
                "inactive_gate",
                TerranIDS.TERRAN
        );
        gate.setCircularOrbit(spacedock, 45f, 1500f, 300f);

        // Drydocks
        addDrydock(system, spacedock, "starbase4_drydock_alpha", "Drydock Alpha", 0f);
        addDrydock(system, spacedock, "starbase4_drydock_beta", "Drydock Beta", 120f);
        addDrydock(system, spacedock, "starbase4_drydock_gamma", "Drydock Gamma", 240f);

        // =========================================================
        // Asteroid Belt (The main band)
        // =========================================================
        system.addAsteroidBelt(star, 250, 7000f, 300f, 150f, 250f, "asteroid_belt", "Malachite Belt");

        // =========================================================
        // Asteroid Fields (Variable density/size to avoid monotony)
        // =========================================================
        // params: system, focus, orbitRadius, minAsteroids, maxAsteroids, minSize, maxSize, width, orbitPeriod, name
        addAsteroidField(system, star, 4000f, 10, 20, 10f, 30f, 200f, 300f, "Alpha Debris Field");
        addAsteroidField(system, star, 5500f, 20, 40, 20f, 50f, 400f, 400f, "Beta Cloud");
        addAsteroidField(system, star, 9000f, 15, 30, 30f, 70f, 300f, 600f, "Gamma Field");
        addAsteroidField(system, star, 11000f, 5, 10, 40f, 100f, 100f, 800f, "Delta Cluster");

        // Scattered "filler" asteroids
        for (int i = 0; i < 40; i++) {
            AsteroidAPI asteroid = system.addAsteroid(10f + (float) (Math.random() * 40f));
            asteroid.setCircularOrbit(star, (float) (Math.random() * 360f), 3000f + (float) (Math.random() * 10000f), 200f + (float) (Math.random() * 300f));
        }

        // Magnetic Field
        MagneticFieldParams magneticField = new MagneticFieldParams(
                1600f, 2800f, star, 1200f, 4400f, new Color(80, 180, 255, 120), 1f, new Color(50, 255, 255), new Color(120, 220, 255)
        );
        SectorEntityToken field = system.addTerrain("magnetic_field", magneticField);
        field.setCircularOrbit(star, 0f, 0f, 100f);

        system.autogenerateHyperspaceJumpPoints(true, true);
    }

    private void addDrydock(StarSystemAPI system, SectorEntityToken anchor, String id, String name, float angle) {
        SectorEntityToken dock = system.addCustomEntity(id, name, "drydock", TerranIDS.TERRAN);
        dock.setCircularOrbit(anchor, angle, 450f, 30f);
    }

    private void addAsteroidField(StarSystemAPI system, SectorEntityToken orbitFocus, float orbitRadius,
                                  int minA, int maxA, float minS, float maxSize, float width, float period, String name) {

        SectorEntityToken field = system.addTerrain("asteroid_field", new AsteroidFieldTerrainPlugin.AsteroidFieldParams(
                width,         // min radius (width of field)
                width + 100f,  // max radius (width of field)
                minA,          // min asteroid count
                maxA,          // max asteroid count
                minS,          // min size
                maxSize,       // max size
                name
        ));

        // Randomize the starting angle so they don't all align
        field.setCircularOrbit(orbitFocus, (float) Math.random() * 360f, orbitRadius, period);
    }
}