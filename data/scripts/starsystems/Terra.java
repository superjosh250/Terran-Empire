package data.scripts.starsystems;

import java.awt.Color;

import TERRAN.data.campaign.ids.TerranIDS;
import UFP.data.campaign.ids.DimensionsCrossedIDS;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;

import com.fs.starfarer.api.impl.campaign.JumpPointInteractionDialogPluginImpl;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;

public class Terra {

    // =========================================================
    // Real-world AU values
    // =========================================================
    private static final double AU_MERCURY = 0.387;
    private static final double AU_VENUS   = 0.723;
    private static final double AU_EARTH   = 1.000; // Terra Prime
    private static final double AU_MARS    = 1.524;
    private static final double AU_BELT    = 2.80;
    private static final double AU_JUPITER = 5.203;
    private static final double AU_SATURN  = 9.537;
    private static final double AU_URANUS  = 19.191;
    private static final double AU_NEPTUNE = 30.069;

    // =========================================================
    // Star + safety
    // =========================================================
    private static final float STAR_RADIUS = 800f;
    private static final float CORONA_EXTRA = 1000f;
    private static final float MIN_ORBIT = STAR_RADIUS + CORONA_EXTRA + 700f;

    // =========================================================
    // Tightened spacing (Neptune guaranteed on-grid)
    // =========================================================
    private static final float ORBIT_SCALE = 3400f;
    private static final float ORBIT_EXP   = 0.55f;

    // =========================================================
    // Orbital period scaling (compressed to match distance)
    // =========================================================
    private static final float EARTH_PERIOD = 365f;
    private static final float PERIOD_EXP   = 0.78f;

    // Planet sizes
    private static final float R_MERCURY = 55f;
    private static final float R_VENUS   = 85f;
    private static final float R_EARTH   = 90f;
    private static final float R_MARS    = 70f;
    private static final float R_JUPITER = 210f;
    private static final float R_SATURN  = 190f;
    private static final float R_URANUS  = 140f;
    private static final float R_NEPTUNE = 140f;

    public void generate(SectorAPI sector) {

        StarSystemAPI system = sector.createStarSystem("Terra");
        system.getLocation().set(20706f, 0f);
        system.setBackgroundTextureFilename("graphics/backgrounds/background2.jpg");
        system.setLightColor(new Color(255, 245, 225));

        PlanetAPI sun = system.initStar("terra_star", "star_yellow", STAR_RADIUS, CORONA_EXTRA, 10f, 0.6f, 3f);
        sun.setName("Terra");
        system.generateAnchorIfNeeded();

        float rMercury = orbitRadiusFromAU(AU_MERCURY);
        float rVenus   = orbitRadiusFromAU(AU_VENUS);
        float rEarth   = orbitRadiusFromAU(AU_EARTH);
        float rMars    = orbitRadiusFromAU(AU_MARS);
        float rBelt    = orbitRadiusFromAU(AU_BELT);
        float rJupiter = orbitRadiusFromAU(AU_JUPITER);
        float rSaturn  = orbitRadiusFromAU(AU_SATURN);
        float rUranus  = orbitRadiusFromAU(AU_URANUS);
        float rNeptune = orbitRadiusFromAU(AU_NEPTUNE);

        // Mercury
        PlanetAPI mercury = system.addPlanet("terran_mercury", sun, "Mercury", "barren", 20f, R_MERCURY, rMercury,
                orbitDaysFromAU(AU_MERCURY));
        applyTilt(mercury, 2f);

        // Venus
        PlanetAPI venus = system.addPlanet("terran_venus", sun, "Venus", "toxic", 80f, R_VENUS, rVenus,
                orbitDaysFromAU(AU_VENUS));
        applyTilt(venus, 177.36f);


        /********** EARTH **********/
        // Terra Prime (Formerly Earth)
        PlanetAPI terraPrime = system.addPlanet("terra_prime", sun, "Terra Prime", "earth", 140f, R_EARTH, rEarth,
                orbitDaysFromAU(AU_EARTH));
        applyTilt(terraPrime, 23.44f);

        // Add jump point near Terra Prime
        addCustomJumpPoint(system, terraPrime, "jp_terra_prime_custom", "Terra Prime Jump Point");

        // Market Hooks (Safely passing our renamed planet entity)
        TerraMarket.addTerraPrimeMarket(sector, terraPrime);
        // ################################################################## \\

        /******** MOON **********/
        PlanetAPI moon = system.addPlanet("terran_luna", terraPrime, "Moon", DimensionsCrossedIDS.PLANET_TERRAFORMING_MOON, 35f, 25f, 320f, 30f);
        applyTilt(moon, 6.68f);

        TerraMarket.addTerraMoonMarket(sector, moon);

        // Terra Prime Comm Relay
        SectorEntityToken terraRelay = system.addCustomEntity("relay_terra_prime", "Terra Prime Comm Relay", "comm_relay", "ufp");
        terraRelay.setOrbit(Global.getFactory().createCircularOrbit(terraPrime, 60f, 600f, 45f));
        // ################################################################## \\


        // ======================== MARS ======================== \\
        PlanetAPI mars = system.addPlanet("terran_mars", sun, "Mars", DimensionsCrossedIDS.PLANET_TERRAFORMED_MARS, 210f, R_MARS, rMars,
                orbitDaysFromAU(AU_MARS));
        applyTilt(mars, 25.19f);

        TerraMarket.addTerranMarsMarket(sector, mars);
        // ======================== MARS ======================== \\

        // Stations around Mars (Stripped down to 1 hub and 1 drydock)
        SectorEntityToken utopiaPlanitiaStation = system.addCustomEntity(
                "terran_utopia_planitia_station",
                "Utopia Planitia",
                "utopia_planitiaStation",
                TerranIDS.TERRAN);
        utopiaPlanitiaStation.setOrbit(Global.getFactory().createCircularOrbit(mars, 45f, 350f, 30f));
        TerraMarket.addTerranUtopiaPlanitiaMarket(sector, utopiaPlanitiaStation);

        SectorEntityToken advDrydock1 = system.addCustomEntity(
                "terran_advDrydock1",
                "Type 4 Drydock",
                "advanced_drydock",
                TerranIDS.TERRAN);
        advDrydock1.setOrbit(Global.getFactory().createCircularOrbit(utopiaPlanitiaStation, 90f, 125f, 50f));
        TerraMarket.addTerraAdvancedDrydock(sector, advDrydock1);

        // Mars relay
        SectorEntityToken marsRelay = system.addCustomEntity("terran_relay_mars", "Mars Comm Relay", "comm_relay", TerranIDS.TERRAN);
        marsRelay.setOrbit(Global.getFactory().createCircularOrbit(mars, 120f, 520f, 50f));

        // =========================================================
        // JUPITER
        // =========================================================
        PlanetAPI jupiter = system.addPlanet("terran_jupiter", sun, "Jupiter", "gas_giant", 300f, R_JUPITER, rJupiter,
                orbitDaysFromAU(AU_JUPITER));
        applyTilt(jupiter, 3.13f);

        // Add jump point near Jupiter
        addCustomJumpPoint(system, jupiter, "terran_jp_jupiter_custom", "Jupiter Jump Point");

        SectorEntityToken jupiterRelay = system.addCustomEntity("terran_relay_jupiter", "Jupiter Comm Relay", "comm_relay", TerranIDS.TERRAN);
        jupiterRelay.setOrbit(Global.getFactory().createCircularOrbit(jupiter, 200f, 1200f, 80f));

        PlanetAPI io       = system.addPlanet("terran_io", jupiter, "Io", "lava_minor", 30f, 45f, 360f, 1.769f);

        // ============== EUROPA ============ \\
        PlanetAPI europa   = system.addPlanet("terran_europa", jupiter, "Europa", "frozen", 90f, 42f, 520f, 3.551f);
        TerraMarket.addEuropaMarket(sector, europa);
        // ============== EUROPA ============ \\

        PlanetAPI ganymede = system.addPlanet("terran_ganymede", jupiter, "Ganymede", "frozen", 150f, 55f, 760f, 7.155f);
        PlanetAPI callisto = system.addPlanet("terran_callisto", jupiter, "Callisto", "barren", 210f, 52f, 1100f, 16.689f);

        // Jupiter Stations (Stripped down to just the main station)
        SectorEntityToken jupiterStation = system.addCustomEntity(
                "terran_jupiterStation",
                "Jupiter Station",
                "jupiterStation",
                TerranIDS.TERRAN);
        jupiterStation.setOrbit(Global.getFactory().createCircularOrbit(jupiter, 150f, 500f, 90f));
        TerraMarket.addTerranJupiterStationMarket(sector, jupiterStation);

        // =========================================================
        // SATURN
        // =========================================================
        PlanetAPI saturn = system.addPlanet("terran_saturn", sun, "Saturn", "saturn", 20f, R_SATURN, rSaturn,
                orbitDaysFromAU(AU_SATURN));
        applyTilt(saturn, 26.73f);

        float saturnRingMid = saturn.getRadius() + 520f;
        system.addRingBand(saturn, "misc", "rings_dust0", 256f, 0, new Color(220, 220, 220, 200),
                420f, saturnRingMid, 40f, Terrain.RING, "Saturn's Rings");
        system.addRingBand(saturn, "misc", "rings_ice0", 256f, 1, new Color(255, 255, 255, 160),
                260f, saturn.getRadius() + 760f, 55f);

        PlanetAPI enceladus = system.addPlanet("terran_enceladus", saturn, "Enceladus", "frozen", 20f, 25f, 320f, 1.370f);
        PlanetAPI rhea      = system.addPlanet("terran_rhea", saturn, "Rhea", "frozen", 80f, 35f, 520f, 4.518f);
        PlanetAPI titan     = system.addPlanet("terran_titan", saturn, "Titan", "frozen", 140f, 60f, 850f, 15.945f);
        PlanetAPI iapetus   = system.addPlanet("terran_iapetus", saturn, "Iapetus", "frozen", 220f, 30f, 1500f, 79.322f);

        // =========================================================
        // URANUS + NEPTUNE
        // =========================================================
        PlanetAPI uranus = system.addPlanet("terran_uranus", sun, "Uranus", "uranus", 70f, R_URANUS, rUranus,
                orbitDaysFromAU(AU_URANUS));
        applyTilt(uranus, 97.77f);

        PlanetAPI neptune = system.addPlanet("terran_neptune", sun, "Neptune", "neptune", 120f, R_NEPTUNE, rNeptune,
                orbitDaysFromAU(AU_NEPTUNE));
        applyTilt(neptune, 28.32f);

        // Add jump point near Neptune
        addCustomJumpPoint(system, neptune, "terran_jp_neptune_custom", "Neptune Jump Point");

        // Asteroid belt
        system.addAsteroidBelt(sun, 1200, rBelt, 1600f, 180f, 420f, Terrain.ASTEROID_BELT, "Main Belt");

        // Fringe jump point
        JumpPointAPI fringe = Global.getFactory().createJumpPoint("terra_jump_fringe", "Fringe Jump-point");
        fringe.setOrbit(Global.getFactory().createCircularOrbit(sun, 120f, rNeptune * 1.08f,
                orbitDaysFromAU(AU_NEPTUNE) + 180f));
        fringe.setStandardWormholeToHyperspaceVisual();
        fringe.getMemoryWithoutUpdate().set(JumpPointInteractionDialogPluginImpl.UNSTABLE_KEY, true);
        system.addEntity(fringe);

        system.autogenerateHyperspaceJumpPoints(true, true);
    }

    private float orbitRadiusFromAU(double au) {
        return MIN_ORBIT + ORBIT_SCALE * (float) Math.pow(au, ORBIT_EXP);
    }

    private float orbitDaysFromAU(double au) {
        return EARTH_PERIOD * (float) Math.pow(au, PERIOD_EXP);
    }

    private void applyTilt(PlanetAPI planet, float tiltDeg) {
        planet.getSpec().setTilt(tiltDeg);
        planet.applySpecChanges();
    }

    private void addCustomJumpPoint(StarSystemAPI system, PlanetAPI target, String id, String name) {
        JumpPointAPI jp = Global.getFactory().createJumpPoint(id, name);
        jp.setOrbit(Global.getFactory().createCircularOrbit(target, 0f, 600f, 100f));
        jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jp);
    }
}