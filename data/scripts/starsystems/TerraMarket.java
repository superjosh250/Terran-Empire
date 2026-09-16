package data.scripts.starsystems;

import TERRAN.data.campaign.ids.TerranIDS;
import UFP.data.campaign.ids.DimensionsCrossedIDS;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;

public class TerraMarket {


    /** Capital World - Massive Population, Political & Military Nexus **/
    public static void addTerraPrimeMarket(SectorAPI sector, SectorEntityToken entity) {
        MarketAPI market = Global.getFactory().createMarket("terra_prime_market", "Terra Prime", 10);
        initBaseMarket(market, entity);

        // Planetary Conditions
        market.addCondition(Conditions.POPULATION_10);
        market.addCondition(Conditions.TERRAN);
        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.MILD_CLIMATE);
        market.addCondition(Conditions.FARMLAND_BOUNTIFUL);
        market.addCondition(Conditions.ORE_RICH);
        market.addCondition(Conditions.RARE_ORE_MODERATE);
        market.addCondition(Conditions.VOLATILES_PLENTIFUL);
        market.addCondition(DimensionsCrossedIDS.DILITHIUM_ORE);
        market.addCondition(Conditions.ESTABLISHED_POLITY);
        market.addCondition(Conditions.STEALTH_MINEFIELDS);

        // Core Infrastructure & Specialized UFP Architecture
        market.addIndustry(DimensionsCrossedIDS.SOCIETY_POPULATION);
        market.addIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_ACADEMY);

        // Economic Output
        market.addIndustry(Industries.FARMING);
        market.addIndustry(DimensionsCrossedIDS.WAYSTATION);
        market.addIndustry(Industries.HEAVYBATTERIES);
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);
        market.addIndustry(TerranIDS.IMPERIAL_CAPITAL);
        market.addIndustry(DimensionsCrossedIDS.ORBITALSTATION_SPACEDOCK);
        market.addIndustry(DimensionsCrossedIDS.DRYDOCK_ADVANCED);
        market.addIndustry(DimensionsCrossedIDS.MINING_ADVANCED);
        market.addIndustry(DimensionsCrossedIDS.REFINING_PROCESSING_PLANT);
        market.addIndustry(DimensionsCrossedIDS.INDUSTRIAL_REPLICATOR);

        Industry terraSociety = market.getIndustry(DimensionsCrossedIDS.SOCIETY_POPULATION);
        if (terraSociety != null) {
            terraSociety.setSpecialItem(new SpecialItemData(Items.DEALMAKER_HOLOSUITE, null));
            terraSociety.setAICoreId(Commodities.OMEGA_CORE);
            terraSociety.setImproved(true);
        }

        Industry terraSpaceport = market.getIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        if (terraSpaceport != null) {
            terraSpaceport.setSpecialItem(new SpecialItemData(Items.DEALMAKER_HOLOSUITE, null));
            terraSpaceport.setAICoreId(Commodities.OMEGA_CORE);
            terraSpaceport.setImproved(true);
        }

        Industry terraDrydock = market.getIndustry(DimensionsCrossedIDS.DRYDOCK);
        if (terraDrydock != null) {
            terraDrydock.setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));
            terraDrydock.setAICoreId(Commodities.OMEGA_CORE);
            terraDrydock.setImproved(true);
        }

        Industry terraDrydockADV = market.getIndustry(DimensionsCrossedIDS.DRYDOCK_ADVANCED);
        if (terraDrydockADV != null) {
            terraDrydockADV.setSpecialItem(new SpecialItemData(Items.PRISTINE_NANOFORGE, null));
            terraDrydockADV.setAICoreId(Commodities.OMEGA_CORE);
            terraDrydockADV.setImproved(true);
        }

        Industry terraMining = market.getIndustry(DimensionsCrossedIDS.MINING_ADVANCED);
        if (terraMining != null) {
            terraMining.setSpecialItem(new SpecialItemData(Items.PLASMA_DYNAMO, null));
            terraMining.setAICoreId(Commodities.ALPHA_CORE);
            terraMining.setImproved(true);
        }

        Industry terraRefining = market.getIndustry(DimensionsCrossedIDS.REFINING_PROCESSING_PLANT);
        if (terraRefining != null) {
            terraRefining.setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));
            terraRefining.setAICoreId(Commodities.ALPHA_CORE);
            terraRefining.setImproved(true);
        }

        Industry terraIndustry = market.getIndustry(DimensionsCrossedIDS.INDUSTRIAL_REPLICATOR);
        if (terraIndustry != null) {
            terraIndustry.setAICoreId(Commodities.ALPHA_CORE);
            terraIndustry.setImproved(true);
        }

        // Submarkets
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.GENERIC_MILITARY);

        finalizeMarket(market);
    }

    /** Luna - High-Tech Scientific Outpost & Fortress Moon **/
    public static void addTerraMoonMarket (SectorAPI sector, SectorEntityToken entity) {
        MarketAPI market = Global.getFactory().createMarket("terran_luna_market", "Luna", 8);
        initBaseMarket(market, entity);

        market.addCondition(Conditions.POPULATION_8);
        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.TERRAN);
        market.addCondition(DimensionsCrossedIDS.TERRAFORMING);
        market.addCondition(Conditions.LOW_GRAVITY);
        market.addCondition(Conditions.FARMLAND_BOUNTIFUL);
        market.addCondition(Conditions.ORGANICS_COMMON);
        market.addCondition(Conditions.VOLATILES_ABUNDANT);
        market.addCondition(Conditions.ORE_ULTRARICH);
        market.addCondition(Conditions.RARE_ORE_ULTRARICH);
        market.addCondition(DimensionsCrossedIDS.DILITHIUM_ORE);
        market.addCondition(Conditions.INDUSTRIAL_POLITY);

        // INDUSTRY
        market.addIndustry(DimensionsCrossedIDS.SOCIETY_POPULATION);
        market.addIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        market.addIndustry(DimensionsCrossedIDS.WAYSTATION);
        market.addIndustry(Industries.FARMING);
        market.addIndustry(DimensionsCrossedIDS.DRYDOCK);
        market.addIndustry(DimensionsCrossedIDS.MINING_ADVANCED);
        market.addIndustry(DimensionsCrossedIDS.REFINING_PROCESSING_PLANT);
        market.addIndustry(DimensionsCrossedIDS.INDUSTRIAL_REPLICATOR);
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);
        market.addIndustry(DimensionsCrossedIDS.SHIPYARD_PLATFORMS);

        Industry terranSociety = market.getIndustry(DimensionsCrossedIDS.SOCIETY_POPULATION);
        if (terranSociety != null) {
            terranSociety.setSpecialItem(new SpecialItemData(Items.DEALMAKER_HOLOSUITE, null));
            terranSociety.setAICoreId(Commodities.ALPHA_CORE);
            terranSociety.setImproved(true);
        }

        Industry terranSpaceport = market.getIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        if (terranSpaceport != null) {
            terranSpaceport.setSpecialItem(new SpecialItemData(Items.DEALMAKER_HOLOSUITE, null));
            terranSpaceport.setAICoreId(Commodities.GAMMA_CORE);
            terranSpaceport.setImproved(true);
        }

        Industry terranDrydock = market.getIndustry(DimensionsCrossedIDS.DRYDOCK);
        if (terranDrydock != null) {
            terranDrydock.setSpecialItem(new SpecialItemData(Items.PRISTINE_NANOFORGE, null));
            terranDrydock.setAICoreId(Commodities.ALPHA_CORE);
            terranDrydock.setImproved(true);
        }

        Industry terranMining = market.getIndustry(DimensionsCrossedIDS.MINING_ADVANCED);
        if (terranMining != null) {
            terranMining.setSpecialItem(new SpecialItemData(Items.PLASMA_DYNAMO, null));
            terranMining.setAICoreId(Commodities.GAMMA_CORE);
            terranMining.setImproved(true);
        }


        // SUBMARKETS
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.GENERIC_MILITARY);

        finalizeMarket(market);
    }

    /** Mars - Developing Terraforming Economic Engine **/
    public static void addTerranMarsMarket (SectorAPI sector, SectorEntityToken entity) {
        MarketAPI market = Global.getFactory().createMarket("terran_mars_market", "Mars", 7);
        initBaseMarket(market, entity);

        market.addCondition(Conditions.POPULATION_7);
        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.TERRAN);
        market.addCondition(DimensionsCrossedIDS.TERRAFORMED);
        market.addCondition(Conditions.THIN_ATMOSPHERE);
        market.addCondition(Conditions.INDUSTRIAL_POLITY);
        market.addCondition(Conditions.VOLATILES_DIFFUSE);
        market.addCondition(Conditions.ORE_ULTRARICH);
        market.addCondition(Conditions.RARE_ORE_ULTRARICH);
        market.addCondition(DimensionsCrossedIDS.DILITHIUM_ORE);

        // INDUSTRY
        market.addIndustry(DimensionsCrossedIDS.SOCIETY_POPULATION);
        market.addIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        market.addIndustry(DimensionsCrossedIDS.ORBITAL_COMPLEX_BASE);
        market.addIndustry(DimensionsCrossedIDS.WAYSTATION);
        market.addIndustry(DimensionsCrossedIDS.DRYDOCK_ADVANCED);
        market.addIndustry(DimensionsCrossedIDS.MINING_ADVANCED);
        market.addIndustry(DimensionsCrossedIDS.REFINING_PROCESSING_PLANT);
        market.addIndustry(DimensionsCrossedIDS.INDUSTRIAL_REPLICATOR);
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);

        Industry t_marsSociety = market.getIndustry(DimensionsCrossedIDS.SOCIETY_POPULATION);
        if (t_marsSociety != null) {
            t_marsSociety.setSpecialItem(new SpecialItemData(Items.DEALMAKER_HOLOSUITE, null));
            t_marsSociety.setAICoreId(Commodities.OMEGA_CORE);
        }

        Industry t_marsSpaceport = market.getIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        if (t_marsSpaceport != null) {
            t_marsSpaceport.setSpecialItem(new SpecialItemData(Items.DEALMAKER_HOLOSUITE, null));
            t_marsSpaceport.setAICoreId(Commodities.OMEGA_CORE);
        }

        Industry t_marsDrydock = market.getIndustry(DimensionsCrossedIDS.DRYDOCK_ADVANCED);
        if (t_marsDrydock != null) {
            t_marsDrydock.setSpecialItem(new SpecialItemData(Items.PRISTINE_NANOFORGE, null));
            t_marsDrydock.setAICoreId(Commodities.ALPHA_CORE);
        }

        Industry t_marsIndustrial = market.getIndustry(DimensionsCrossedIDS.INDUSTRIAL_REPLICATOR);
        if (t_marsIndustrial != null) {
            t_marsIndustrial.setAICoreId(Commodities.ALPHA_CORE);
            t_marsIndustrial.setImproved(true);
        }

        // SUBMARKETS
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.GENERIC_MILITARY);

        finalizeMarket(market);
    }

    /** Utopia Planitia - The Premier Logistical & Heavy Industrial Shipyard Hub **/
    public static void addTerranUtopiaPlanitiaMarket (SectorAPI sector, SectorEntityToken entity) {
        MarketAPI market = Global.getFactory().createMarket("terran_utopia_planitia_market", "Utopia Planitia", 5);
        initBaseMarket(market, entity);

        // CONDITIONS
        market.addCondition(Conditions.POPULATION_5);
        market.addCondition(DimensionsCrossedIDS.STARBASE_HABITAT);
        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.MILD_CLIMATE);
        market.addCondition(Conditions.INDUSTRIAL_POLITY);
        market.addCondition(Conditions.OUTPOST);

        // INDUSTRY
        market.addIndustry(DimensionsCrossedIDS.STATION_POPULATION);
        market.addIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        market.addIndustry(DimensionsCrossedIDS.WAYSTATION);
        market.addIndustry(Industries.COMMERCE);
        market.addIndustry(Industries.LIGHTINDUSTRY);
        market.addIndustry(Industries.FUELPROD);
        market.addIndustry(DimensionsCrossedIDS.INDUSTRIAL_REPLICATOR);
        market.addIndustry(DimensionsCrossedIDS.ORBITALSTATION_UTP);
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);

        Industry utopiaPopulation = market.getIndustry(DimensionsCrossedIDS.STATION_POPULATION);
        if (utopiaPopulation != null) {
            utopiaPopulation.setSpecialItem(new SpecialItemData(Items.DEALMAKER_HOLOSUITE, null));
            utopiaPopulation.setAICoreId(Commodities.OMEGA_CORE);
        }

        Industry utopiaOps = market.getIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);
        if (utopiaOps != null) {
            utopiaOps.setAICoreId(Commodities.GAMMA_CORE);
            utopiaOps.setImproved(true);
        }

        // SUBMARKETS
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.GENERIC_MILITARY);

        finalizeMarket(market);
    }

    /** Type 4 Drydock - Auxiliary High-Security Military Shipyard Satellite **/
    public static void addTerraAdvancedDrydock (SectorAPI sector, SectorEntityToken entity) {
        MarketAPI market = Global.getFactory().createMarket("terran_adv_drydock_market", "Type 4 Drydock", 5);
        initBaseMarket(market, entity);

        // CONDITIONS
        market.addCondition(Conditions.POPULATION_5);
        market.addCondition(DimensionsCrossedIDS.STARBASE_HABITAT);
        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.MILD_CLIMATE);
        market.addCondition(Conditions.INDUSTRIAL_POLITY);
        market.addCondition(Conditions.OUTPOST);

        // INDUSTRY
        market.addIndustry(DimensionsCrossedIDS.STATION_POPULATION);
        market.addIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        market.addIndustry(DimensionsCrossedIDS.WAYSTATION);
        market.addIndustry(DimensionsCrossedIDS.INDUSTRIAL_REPLICATOR);
        market.addIndustry(DimensionsCrossedIDS.DRYDOCK_ADVANCED); // Specialized Tactical Drydock
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);

        Industry t_advDrydock = market.getIndustry(DimensionsCrossedIDS.DRYDOCK_ADVANCED);
        if (t_advDrydock != null) {
            t_advDrydock.setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));
            t_advDrydock.setAICoreId(Commodities.ALPHA_CORE);
            t_advDrydock.setImproved(true);
        }

        Industry t_advDrydock_ops = market.getIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);
        if (t_advDrydock_ops != null) {
            t_advDrydock_ops.setAICoreId(Commodities.GAMMA_CORE);
            t_advDrydock_ops.setImproved(true);
        }

        // SUBMARKETS
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        market.addSubmarket(Submarkets.GENERIC_MILITARY);

        finalizeMarket(market);
    }

    /** Europa - Ocean Under-Ice Cryo-Mining & Harvesting Operation **/
    public static void addEuropaMarket(SectorAPI sector, SectorEntityToken entity) {
        MarketAPI market = Global.getFactory().createMarket("terran_europa_market", "Europa", 6);
        initBaseMarket(market, entity);

        // CONDITIONS
        market.addCondition(Conditions.POPULATION_6);
        market.addCondition(Conditions.WATER_SURFACE);
        market.addCondition(Conditions.COLD);
        market.addCondition(Conditions.VOLATILES_ABUNDANT);
        market.addCondition(Conditions.INDUSTRIAL_POLITY);

        // INDUSTRY
        market.addIndustry(DimensionsCrossedIDS.SOCIETY_POPULATION);
        market.addIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        market.addIndustry(DimensionsCrossedIDS.ORBITALSTATION_JUPITER);
        market.addIndustry(DimensionsCrossedIDS.WAYSTATION);
        market.addIndustry(Industries.AQUACULTURE);
        market.addIndustry(DimensionsCrossedIDS.MINING_ADVANCED);
        market.addIndustry(DimensionsCrossedIDS.REFINING_PROCESSING_PLANT);
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);

        Industry t_europaRefining = market.getIndustry(DimensionsCrossedIDS.REFINING_PROCESSING_PLANT);
        if (t_europaRefining != null) {
            t_europaRefining.setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));
            t_europaRefining.setAICoreId(Commodities.OMEGA_CORE);
            t_europaRefining.setImproved(true);
        }

        Industry t_europaOPS = market.getIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);
        if (t_europaOPS != null) {
            t_europaOPS.setAICoreId(Commodities.GAMMA_CORE);
        }

        // SUBMARKET
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addSubmarket(Submarkets.SUBMARKET_BLACK);
        market.addSubmarket(Submarkets.GENERIC_MILITARY);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);

        finalizeMarket(market);
    }

    /** Jupiter Station - Outer Rim Logistical Fuel Array & Deep Space Salvage Base **/
    public static void addTerranJupiterStationMarket (SectorAPI sector, SectorEntityToken entity) {
        MarketAPI market = Global.getFactory().createMarket("terran_jupiter_station_market", "Jupiter Station", 5);
        initBaseMarket(market, entity);

        // CONDITIONS
        market.addCondition(Conditions.POPULATION_5);
        market.addCondition(DimensionsCrossedIDS.STARBASE_HABITAT);
        market.addCondition(Conditions.HABITABLE);
        market.addCondition(Conditions.MILD_CLIMATE);
        market.addCondition(Conditions.ESTABLISHED_POLITY);
        market.addCondition(Conditions.OUTPOST);

        // INDUSTRY
        market.addIndustry(DimensionsCrossedIDS.STATION_POPULATION);
        market.addIndustry(DimensionsCrossedIDS.STARBASE_SPACEPORT);
        market.addIndustry(Industries.COMMERCE);
        market.addIndustry(DimensionsCrossedIDS.WAYSTATION);
        market.addIndustry(DimensionsCrossedIDS.UNIVERSITY);
        market.addIndustry(DimensionsCrossedIDS.R_N_D);
        market.addIndustry(DimensionsCrossedIDS.SALVAGE_OPS);
        market.addIndustry(DimensionsCrossedIDS.STARFLEET_OPERATIONS);
        market.addIndustry(DimensionsCrossedIDS.ORBITALSTATION_JUPITER);

        // SUBMARKETS
        market.addSubmarket(Submarkets.SUBMARKET_OPEN);
        market.addSubmarket(Submarkets.GENERIC_MILITARY);
        market.addSubmarket(Submarkets.SUBMARKET_STORAGE);

        finalizeMarket(market);
    }

    // =========================================================
    // Shared Internal Cleanliness Routines
    // =========================================================

    private static void initBaseMarket(MarketAPI market, SectorEntityToken entity) {
        market.setFactionId(TerranIDS.TERRAN);
        market.setPrimaryEntity(entity);

        // If it's a planet, link it correctly for the UI to map hazard rating & features.
        if (entity instanceof PlanetAPI) {
            market.setPrimaryEntity((PlanetAPI) entity);
        }

        market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);
        entity.setMarket(market);
        entity.setFaction(TerranIDS.TERRAN);
    }

    private static void finalizeMarket(MarketAPI market) {
        Global.getSector().getEconomy().addMarket(market, true);
        market.reapplyIndustries(); // Automatically reapplies industry math with the new Cores and Items
    }
}