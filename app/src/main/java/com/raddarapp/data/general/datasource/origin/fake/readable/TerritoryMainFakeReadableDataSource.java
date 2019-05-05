package com.raddarapp.data.general.datasource.origin.fake.readable;

import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoryMainReadableDataSourceContract;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.SimpleLocation;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.domain.model.TerritoryMain;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.TerritoryBuilder;
import com.raddarapp.domain.model.builder.TerritoryMainBuilder;
import com.raddarapp.domain.model.util.GenerateUsers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TerritoryMainFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, TerritoryMain>
    implements TerritoryMainReadableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private int primaryKey = 0;
    private static final String MONTEQUINTO = "Montequinto";
    private static final String DOS_HERMANAS = "Dos Hermanas";
    private static final String ALFALFA = "Alfalfa";
    private static final String WORLD = "World";
    private static final String EMOJI_WORLD = ":earth_africa:";
    private GenerateUsers generateUsers = new GenerateUsers();

    @Inject
    public TerritoryMainFakeReadableDataSource() {
    }

    @Override
    public TerritoryMain getTerritoryMainByCoordinates(RaddarLocation raddarLocation) throws Exception {
        fakeDelay();

        return generateTerritoryMainMontequinto();
    }

    @Override
    public TerritoryMain getTerritoryMainByZone(String zoneKey) throws Exception {
        fakeDelay();

        TerritoryMain territoryMain;

        if (zoneKey.equals(MONTEQUINTO)) {
            territoryMain = generateTerritoryMainMontequinto();
        } else if (zoneKey.equals(DOS_HERMANAS)){
            territoryMain = generateTerritoryMainDosHermanas();
        } else if (zoneKey.equals(ALFALFA)){
            territoryMain = generateTerritoryMainAlfalfa();
        } else {
            territoryMain = generateTerritoryMainWorld();
        }

        return territoryMain;
    }

    private TerritoryMain generateTerritoryMainMontequinto() {
        final Territory territory = getMontequinto();
        final TerritoryArea territoryArea = generateMontequinto();
        final TerritoryMain territoryMain = new TerritoryMainBuilder()
                .withKey(MONTEQUINTO)
                .withTerritory(territory)
                .withTerritoryArea(territoryArea)
                .build();

        return territoryMain;
    }

    private TerritoryMain generateTerritoryMainDosHermanas() {
        final Territory territory = getDosHermanas();
        final TerritoryArea territoryArea = generateDosHermanas();
        final TerritoryMain territoryMain = new TerritoryMainBuilder()
                .withKey(DOS_HERMANAS)
                .withTerritory(territory)
                .withTerritoryArea(territoryArea)
                .build();

        return territoryMain;
    }

    private TerritoryMain generateTerritoryMainAlfalfa() {
        final Territory territory = getAlfalfa();
        final TerritoryArea territoryArea = generateAlfalfa();
        final TerritoryMain territoryMain = new TerritoryMainBuilder()
                .withKey(ALFALFA)
                .withTerritory(territory)
                .withTerritoryArea(territoryArea)
                .build();

        return territoryMain;
    }

    private TerritoryMain generateTerritoryMainWorld() {
        final Territory territory = getWorld();
        final TerritoryArea territoryArea = generateWorld();
        final TerritoryMain territoryMain = new TerritoryMainBuilder()
                .withKey(WORLD)
                .withTerritory(territory)
                .withTerritoryArea(territoryArea)
                .build();

        return territoryMain;
    }

    // 45 points
    private TerritoryArea generateMontequinto() {
        List<List<SimpleLocation>> locations = new ArrayList<>();
        List<SimpleLocation> territoryArea = new ArrayList<>();

        territoryArea.add(new SimpleLocation(37.319262899999998, -5.9336018));
        territoryArea.add(new SimpleLocation(37.3192629, -5.9336018));
        territoryArea.add(new SimpleLocation(37.320069400000001, -5.9306214));
        territoryArea.add(new SimpleLocation(37.322343400000001, -5.9293927));
        territoryArea.add(new SimpleLocation(37.324224999999998, -5.9260131));
        territoryArea.add(new SimpleLocation(37.322982600000003, -5.925324));
        territoryArea.add(new SimpleLocation(37.322811399999999, -5.9251663));
        territoryArea.add(new SimpleLocation(37.322685300000003, -5.9250279));
        territoryArea.add(new SimpleLocation(37.322665800000003, -5.9249804));
        territoryArea.add(new SimpleLocation(37.322577500000001, -5.9247115));
        territoryArea.add(new SimpleLocation(37.322550999999997, -5.9244387));
        territoryArea.add(new SimpleLocation(37.322522800000002, -5.923057));
        territoryArea.add(new SimpleLocation(37.322419199999999, -5.9197276));
        territoryArea.add(new SimpleLocation(37.322687500000001, -5.918615));
        territoryArea.add(new SimpleLocation(37.323256200000003, -5.9180661));
        territoryArea.add(new SimpleLocation(37.325338600000002, -5.9180004));
        territoryArea.add(new SimpleLocation(37.326092000000003, -5.9193402));
        territoryArea.add(new SimpleLocation(37.328586299999998, -5.9209077));
        territoryArea.add(new SimpleLocation(37.330906800000001, -5.9196202));
        territoryArea.add(new SimpleLocation(37.335227699999997, -5.9203715));
        territoryArea.add(new SimpleLocation(37.335538399999997, -5.9207962));
        territoryArea.add(new SimpleLocation(37.336552699999999, -5.9232038));
        territoryArea.add(new SimpleLocation(37.337556999999997, -5.9245738));
        territoryArea.add(new SimpleLocation(37.340026299999998, -5.9265794));
        territoryArea.add(new SimpleLocation(37.343502299999997, -5.9316236));
        territoryArea.add(new SimpleLocation(37.3490538, -5.9229012));
        territoryArea.add(new SimpleLocation(37.354380399999997, -5.9279458));
        territoryArea.add(new SimpleLocation(37.352059500000003, -5.9316209));
        territoryArea.add(new SimpleLocation(37.352913399999998, -5.932026));
        territoryArea.add(new SimpleLocation(37.3532242, -5.9331039));
        territoryArea.add(new SimpleLocation(37.351516599999997, -5.9340607));
        territoryArea.add(new SimpleLocation(37.350895100000002, -5.9336625));
        territoryArea.add(new SimpleLocation(37.349528499999998, -5.9358925));
        territoryArea.add(new SimpleLocation(37.3495828, -5.9361317));
        territoryArea.add(new SimpleLocation(37.350172000000001, -5.9366847));
        territoryArea.add(new SimpleLocation(37.349140800000001, -5.9389738));
        territoryArea.add(new SimpleLocation(37.3475769, -5.9399037));
        territoryArea.add(new SimpleLocation(37.344077499999997, -5.9409062));
        territoryArea.add(new SimpleLocation(37.343185499999997, -5.940667));
        territoryArea.add(new SimpleLocation(37.336037900000001, -5.9394582));
        territoryArea.add(new SimpleLocation(37.332210199999999, -5.9367298));
        territoryArea.add(new SimpleLocation(37.331418499999998, -5.9363543));
        territoryArea.add(new SimpleLocation(37.3263514, -5.942334));
        territoryArea.add(new SimpleLocation(37.324033999999997, -5.9395669));
        territoryArea.add(new SimpleLocation(37.323437900000002, -5.9392252));
        territoryArea.add(new SimpleLocation(37.319262899999998, -5.9336018));

        locations.add(territoryArea);

        return new TerritoryArea(MONTEQUINTO, locations);
    }

    // 458 points
    private TerritoryArea generateDosHermanas() {
        List<List<SimpleLocation>> locations = new ArrayList<>();
        List<SimpleLocation> territoryArea = new ArrayList<>();

        territoryArea.add(new SimpleLocation(37.2976625, -6.0402649));
        territoryArea.add(new SimpleLocation(37.2958893, -6.0408928));
        territoryArea.add(new SimpleLocation(37.2957679, -6.0403578));
        territoryArea.add(new SimpleLocation(37.2955666, -6.0397745));
        territoryArea.add(new SimpleLocation(37.2942726, -6.0371848));
        territoryArea.add(new SimpleLocation(37.2938826, -6.0365827));
        territoryArea.add(new SimpleLocation(37.2891162, -6.0306058));
        territoryArea.add(new SimpleLocation(37.2857697, -6.0265246));
        territoryArea.add(new SimpleLocation(37.283417, -6.0237465));
        territoryArea.add(new SimpleLocation(37.282044, -6.0217633));
        territoryArea.add(new SimpleLocation(37.2808665, -6.0202391));
        territoryArea.add(new SimpleLocation(37.2796658, -6.0189169));
        territoryArea.add(new SimpleLocation(37.2764729, -6.0162185));
        territoryArea.add(new SimpleLocation(37.2747994, -6.0147085));
        territoryArea.add(new SimpleLocation(37.2718233, -6.0116245));
        territoryArea.add(new SimpleLocation(37.271478, -6.0113852));
        territoryArea.add(new SimpleLocation(37.2702444, -6.0109978));
        territoryArea.add(new SimpleLocation(37.2699807, -6.0075496));
        territoryArea.add(new SimpleLocation(37.2697297, -6.0051872));
        territoryArea.add(new SimpleLocation(37.2695008, -6.0040048));
        territoryArea.add(new SimpleLocation(37.2682526, -6.0024813));
        territoryArea.add(new SimpleLocation(37.2676293, -6.0019771));
        territoryArea.add(new SimpleLocation(37.2654008, -6.0000867));
        territoryArea.add(new SimpleLocation(37.2643335, -5.9990181));
        territoryArea.add(new SimpleLocation(37.2605815, -5.9963595));
        territoryArea.add(new SimpleLocation(37.2593058, -5.9952179));
        territoryArea.add(new SimpleLocation(37.2573367, -5.9933168));
        territoryArea.add(new SimpleLocation(37.2559345, -5.9914886));
        territoryArea.add(new SimpleLocation(37.2535758, -5.9899651));
        territoryArea.add(new SimpleLocation(37.2521218, -5.9897725));
        territoryArea.add(new SimpleLocation(37.2509557, -5.989918));
        territoryArea.add(new SimpleLocation(37.2493006, -5.9894468));
        territoryArea.add(new SimpleLocation(37.24771, -5.9889218));
        territoryArea.add(new SimpleLocation(37.2469097, -5.9888564));
        territoryArea.add(new SimpleLocation(37.2458335, -5.988656));
        territoryArea.add(new SimpleLocation(37.2440876, -5.9882151));
        territoryArea.add(new SimpleLocation(37.2435279, -5.9880723));
        territoryArea.add(new SimpleLocation(37.2432499, -5.9878439));
        territoryArea.add(new SimpleLocation(37.2370479, -5.9911603));
        territoryArea.add(new SimpleLocation(37.233258, -5.9928023));
        territoryArea.add(new SimpleLocation(37.2323077, -5.9929676));
        territoryArea.add(new SimpleLocation(37.2310818, -5.9929867));
        territoryArea.add(new SimpleLocation(37.2296012, -5.9941677));
        territoryArea.add(new SimpleLocation(37.2263729, -5.9960457));
        territoryArea.add(new SimpleLocation(37.2247724, -5.9973345));
        territoryArea.add(new SimpleLocation(37.2230441, -5.9990464));
        territoryArea.add(new SimpleLocation(37.2218624, -5.9994503));
        territoryArea.add(new SimpleLocation(37.2198868, -5.9999016));
        territoryArea.add(new SimpleLocation(37.2189519, -6.0001688));
        territoryArea.add(new SimpleLocation(37.2181321, -6.0005194));
        territoryArea.add(new SimpleLocation(37.2176624, -6.0009176));
        territoryArea.add(new SimpleLocation(37.2172724, -6.0013754));
        territoryArea.add(new SimpleLocation(37.2163708, -6.0028156));
        territoryArea.add(new SimpleLocation(37.2154017, -6.0044333));
        territoryArea.add(new SimpleLocation(37.2139881, -6.005808));
        territoryArea.add(new SimpleLocation(37.212385, -6.0071977));
        territoryArea.add(new SimpleLocation(37.2115264, -6.0080086));
        territoryArea.add(new SimpleLocation(37.2098889, -6.0093292));
        territoryArea.add(new SimpleLocation(37.2094509, -6.0095484));
        territoryArea.add(new SimpleLocation(37.2076998, -6.0100195));
        territoryArea.add(new SimpleLocation(37.2067942, -6.0101976));
        territoryArea.add(new SimpleLocation(37.2051742, -6.0108317));
        territoryArea.add(new SimpleLocation(37.2036767, -6.0112565));
        territoryArea.add(new SimpleLocation(37.2031474, -6.0115171));
        territoryArea.add(new SimpleLocation(37.2024763, -6.0120424));
        territoryArea.add(new SimpleLocation(37.201901, -6.0126954));
        territoryArea.add(new SimpleLocation(37.2010604, -6.0135068));
        territoryArea.add(new SimpleLocation(37.2003082, -6.0140288));
        territoryArea.add(new SimpleLocation(37.1993334, -6.014801));
        territoryArea.add(new SimpleLocation(37.1965462, -6.0173934));
        territoryArea.add(new SimpleLocation(37.1942693, -6.0197582));
        territoryArea.add(new SimpleLocation(37.1912021, -6.022722));
        territoryArea.add(new SimpleLocation(37.1886908, -6.0254374));
        territoryArea.add(new SimpleLocation(37.187285, -6.0272058));
        territoryArea.add(new SimpleLocation(37.1865825, -6.0278985));
        territoryArea.add(new SimpleLocation(37.1853889, -6.0294726));
        territoryArea.add(new SimpleLocation(37.1841738, -6.0311808));
        territoryArea.add(new SimpleLocation(37.1833122, -6.0321036));
        territoryArea.add(new SimpleLocation(37.1819544, -6.0334007));
        territoryArea.add(new SimpleLocation(37.1815427, -6.0327404));
        territoryArea.add(new SimpleLocation(37.1806255, -6.0316114));
        territoryArea.add(new SimpleLocation(37.1795906, -6.0305002));
        territoryArea.add(new SimpleLocation(37.1782079, -6.0292513));
        territoryArea.add(new SimpleLocation(37.1769251, -6.0279726));
        territoryArea.add(new SimpleLocation(37.1762606, -6.0275294));
        territoryArea.add(new SimpleLocation(37.1755668, -6.0271752));
        territoryArea.add(new SimpleLocation(37.1749861, -6.0269831));
        territoryArea.add(new SimpleLocation(37.1743673, -6.0268683));
        territoryArea.add(new SimpleLocation(37.1731602, -6.0268539));
        territoryArea.add(new SimpleLocation(37.1721634, -6.0270731));
        territoryArea.add(new SimpleLocation(37.1706562, -6.0275197));
        territoryArea.add(new SimpleLocation(37.1700634, -6.0278));
        territoryArea.add(new SimpleLocation(37.1693199, -6.0283333));
        territoryArea.add(new SimpleLocation(37.1686961, -6.0291191));
        territoryArea.add(new SimpleLocation(37.1682923, -6.029756));
        territoryArea.add(new SimpleLocation(37.1675416, -6.0316288));
        territoryArea.add(new SimpleLocation(37.1674307, -6.032086));
        territoryArea.add(new SimpleLocation(37.1672238, -6.0352416));
        territoryArea.add(new SimpleLocation(37.1670969, -6.0391322));
        territoryArea.add(new SimpleLocation(37.1668515, -6.0406199));
        territoryArea.add(new SimpleLocation(37.1663703, -6.044271));
        territoryArea.add(new SimpleLocation(37.1660304, -6.0462728));
        territoryArea.add(new SimpleLocation(37.1636479, -6.0530674));
        territoryArea.add(new SimpleLocation(37.1624795, -6.0557449));
        territoryArea.add(new SimpleLocation(37.1618332, -6.0570474));
        territoryArea.add(new SimpleLocation(37.1609307, -6.0584971));
        territoryArea.add(new SimpleLocation(37.1600916, -6.0595891));
        territoryArea.add(new SimpleLocation(37.1586174, -6.0611958));
        territoryArea.add(new SimpleLocation(37.1579103, -6.0617077));
        territoryArea.add(new SimpleLocation(37.157201, -6.0619605));
        territoryArea.add(new SimpleLocation(37.1563345, -6.0620156));
        territoryArea.add(new SimpleLocation(37.1553944, -6.0617863));
        territoryArea.add(new SimpleLocation(37.1547431, -6.0615348));
        territoryArea.add(new SimpleLocation(37.1540497, -6.0611692));
        territoryArea.add(new SimpleLocation(37.1534039, -6.0607041));
        territoryArea.add(new SimpleLocation(37.1526557, -6.060021));
        territoryArea.add(new SimpleLocation(37.1521487, -6.0594152));
        territoryArea.add(new SimpleLocation(37.1516984, -6.0587104));
        territoryArea.add(new SimpleLocation(37.1512948, -6.0579399));
        territoryArea.add(new SimpleLocation(37.1510808, -6.0568056));
        territoryArea.add(new SimpleLocation(37.1508853, -6.0553006));
        territoryArea.add(new SimpleLocation(37.1507798, -6.0527523));
        territoryArea.add(new SimpleLocation(37.1507754, -6.0515252));
        territoryArea.add(new SimpleLocation(37.1510255, -6.04916));
        territoryArea.add(new SimpleLocation(37.1517026, -6.0452586));
        territoryArea.add(new SimpleLocation(37.1519858, -6.0438555));
        territoryArea.add(new SimpleLocation(37.1517272, -6.0425466));
        territoryArea.add(new SimpleLocation(37.1515907, -6.0418995));
        territoryArea.add(new SimpleLocation(37.1512505, -6.0411092));
        territoryArea.add(new SimpleLocation(37.1509584, -6.0405571));
        territoryArea.add(new SimpleLocation(37.1505584, -6.0399895));
        territoryArea.add(new SimpleLocation(37.1500853, -6.039464));
        territoryArea.add(new SimpleLocation(37.1537294, -6.034049));
        territoryArea.add(new SimpleLocation(37.154034, -6.0313031));
        territoryArea.add(new SimpleLocation(37.1546294, -6.0281073));
        territoryArea.add(new SimpleLocation(37.1555055, -6.0241459));
        territoryArea.add(new SimpleLocation(37.1569526, -6.0189912));
        territoryArea.add(new SimpleLocation(37.1606001, -6.0066621));
        territoryArea.add(new SimpleLocation(37.1625981, -5.9996484));
        territoryArea.add(new SimpleLocation(37.1639162, -5.9963453));
        territoryArea.add(new SimpleLocation(37.1690758, -5.9879243));
        territoryArea.add(new SimpleLocation(37.1701733, -5.986211));
        territoryArea.add(new SimpleLocation(37.1717523, -5.9843364));
        territoryArea.add(new SimpleLocation(37.1737549, -5.9813862));
        territoryArea.add(new SimpleLocation(37.1756049, -5.979128));
        territoryArea.add(new SimpleLocation(37.1768623, -5.9774995));
        territoryArea.add(new SimpleLocation(37.1787341, -5.9754447));
        territoryArea.add(new SimpleLocation(37.1835844, -5.9710402));
        territoryArea.add(new SimpleLocation(37.1860913, -5.9684468));
        territoryArea.add(new SimpleLocation(37.1875481, -5.9671298));
        territoryArea.add(new SimpleLocation(37.1888731, -5.9660329));
        territoryArea.add(new SimpleLocation(37.1897542, -5.9650312));
        territoryArea.add(new SimpleLocation(37.190105, -5.9643353));
        territoryArea.add(new SimpleLocation(37.1932796, -5.9570144));
        territoryArea.add(new SimpleLocation(37.19371, -5.9556571));
        territoryArea.add(new SimpleLocation(37.1940841, -5.9551085));
        territoryArea.add(new SimpleLocation(37.194517, -5.9547311));
        territoryArea.add(new SimpleLocation(37.1953115, -5.9543116));
        territoryArea.add(new SimpleLocation(37.1967858, -5.9533666));
        territoryArea.add(new SimpleLocation(37.1976876, -5.9529737));
        territoryArea.add(new SimpleLocation(37.1971683, -5.9521199));
        territoryArea.add(new SimpleLocation(37.1973181, -5.9511683));
        territoryArea.add(new SimpleLocation(37.1970457, -5.9501665));
        territoryArea.add(new SimpleLocation(37.1967098, -5.9491847));
        territoryArea.add(new SimpleLocation(37.1974391, -5.9484809));
        territoryArea.add(new SimpleLocation(37.1975514, -5.9483163));
        territoryArea.add(new SimpleLocation(37.1978002, -5.9473685));
        territoryArea.add(new SimpleLocation(37.1978855, -5.9472029));
        territoryArea.add(new SimpleLocation(37.1980418, -5.9470738));
        territoryArea.add(new SimpleLocation(37.198614, -5.9468933));
        territoryArea.add(new SimpleLocation(37.1992548, -5.9461297));
        territoryArea.add(new SimpleLocation(37.1993627, -5.9450525));
        territoryArea.add(new SimpleLocation(37.1992379, -5.94391));
        territoryArea.add(new SimpleLocation(37.1992814, -5.9436076));
        territoryArea.add(new SimpleLocation(37.199615, -5.9432375));
        territoryArea.add(new SimpleLocation(37.2004355, -5.9421317));
        territoryArea.add(new SimpleLocation(37.2006478, -5.9419259));
        territoryArea.add(new SimpleLocation(37.2011815, -5.9418452));
        territoryArea.add(new SimpleLocation(37.2013142, -5.9412196));
        territoryArea.add(new SimpleLocation(37.2013669, -5.9405457));
        territoryArea.add(new SimpleLocation(37.2015455, -5.9398881));
        territoryArea.add(new SimpleLocation(37.2016787, -5.9396004));
        territoryArea.add(new SimpleLocation(37.2019009, -5.9393611));
        territoryArea.add(new SimpleLocation(37.202167, -5.9391687));
        territoryArea.add(new SimpleLocation(37.2029876, -5.9387837));
        territoryArea.add(new SimpleLocation(37.2031116, -5.9385069));
        territoryArea.add(new SimpleLocation(37.20328, -5.9378939));
        territoryArea.add(new SimpleLocation(37.2038278, -5.9368787));
        territoryArea.add(new SimpleLocation(37.2042826, -5.9363444));
        territoryArea.add(new SimpleLocation(37.204165, -5.9352697));
        territoryArea.add(new SimpleLocation(37.2055584, -5.932496));
        territoryArea.add(new SimpleLocation(37.2056483, -5.932139));
        territoryArea.add(new SimpleLocation(37.2056177, -5.9311916));
        territoryArea.add(new SimpleLocation(37.205681, -5.93009));
        territoryArea.add(new SimpleLocation(37.2058039, -5.9298582));
        territoryArea.add(new SimpleLocation(37.2060634, -5.9295641));
        territoryArea.add(new SimpleLocation(37.2067075, -5.9290257));
        territoryArea.add(new SimpleLocation(37.207088, -5.9285785));
        territoryArea.add(new SimpleLocation(37.2073278, -5.9279907));
        territoryArea.add(new SimpleLocation(37.2079307, -5.9258398));
        territoryArea.add(new SimpleLocation(37.2088384, -5.9237456));
        territoryArea.add(new SimpleLocation(37.2090918, -5.9233385));
        territoryArea.add(new SimpleLocation(37.209395, -5.9231024));
        territoryArea.add(new SimpleLocation(37.2102717, -5.9226292));
        territoryArea.add(new SimpleLocation(37.211061, -5.9224118));
        territoryArea.add(new SimpleLocation(37.2119992, -5.9223692));
        territoryArea.add(new SimpleLocation(37.2126357, -5.9221346));
        territoryArea.add(new SimpleLocation(37.2130147, -5.9221154));
        territoryArea.add(new SimpleLocation(37.2147859, -5.9215416));
        territoryArea.add(new SimpleLocation(37.2157359, -5.9213867));
        territoryArea.add(new SimpleLocation(37.2165393, -5.9213275));
        territoryArea.add(new SimpleLocation(37.2175081, -5.9211396));
        territoryArea.add(new SimpleLocation(37.2179742, -5.9208759));
        territoryArea.add(new SimpleLocation(37.2182689, -5.9206168));
        territoryArea.add(new SimpleLocation(37.2185572, -5.9202561));
        territoryArea.add(new SimpleLocation(37.2189727, -5.9194834));
        territoryArea.add(new SimpleLocation(37.2202238, -5.9180782));
        territoryArea.add(new SimpleLocation(37.2203521, -5.9178169));
        territoryArea.add(new SimpleLocation(37.2208594, -5.9167843));
        territoryArea.add(new SimpleLocation(37.2211571, -5.9160409));
        territoryArea.add(new SimpleLocation(37.2213478, -5.9152482));
        territoryArea.add(new SimpleLocation(37.2215274, -5.9141734));
        territoryArea.add(new SimpleLocation(37.2218428, -5.9134419));
        territoryArea.add(new SimpleLocation(37.2228225, -5.9117105));
        territoryArea.add(new SimpleLocation(37.2239464, -5.9103452));
        territoryArea.add(new SimpleLocation(37.2242872, -5.9100428));
        territoryArea.add(new SimpleLocation(37.2246534, -5.909809));
        territoryArea.add(new SimpleLocation(37.2250267, -5.9096543));
        territoryArea.add(new SimpleLocation(37.2255431, -5.9095389));
        territoryArea.add(new SimpleLocation(37.225783, -5.9093115));
        territoryArea.add(new SimpleLocation(37.2260629, -5.908556));
        territoryArea.add(new SimpleLocation(37.2263454, -5.9080598));
        territoryArea.add(new SimpleLocation(37.2264811, -5.9076706));
        territoryArea.add(new SimpleLocation(37.226473, -5.9072646));
        territoryArea.add(new SimpleLocation(37.2262604, -5.9067494));
        territoryArea.add(new SimpleLocation(37.225864, -5.906013));
        territoryArea.add(new SimpleLocation(37.2256299, -5.9052716));
        territoryArea.add(new SimpleLocation(37.2256027, -5.9049099));
        territoryArea.add(new SimpleLocation(37.2256596, -5.9044276));
        territoryArea.add(new SimpleLocation(37.2257953, -5.9040384));
        territoryArea.add(new SimpleLocation(37.2260838, -5.9036663));
        territoryArea.add(new SimpleLocation(37.2265447, -5.9032445));
        territoryArea.add(new SimpleLocation(37.2269899, -5.9030926));
        territoryArea.add(new SimpleLocation(37.2273784, -5.9030511));
        territoryArea.add(new SimpleLocation(37.2275541, -5.9028663));
        territoryArea.add(new SimpleLocation(37.2277988, -5.9020756));
        territoryArea.add(new SimpleLocation(37.2280709, -5.9016353));
        territoryArea.add(new SimpleLocation(37.2283926, -5.9013772));
        territoryArea.add(new SimpleLocation(37.2287906, -5.9013135));
        territoryArea.add(new SimpleLocation(37.2293292, -5.9010299));
        territoryArea.add(new SimpleLocation(37.2295044, -5.9008676));
        territoryArea.add(new SimpleLocation(37.2298777, -5.9007128));
        territoryArea.add(new SimpleLocation(37.2309759, -5.9007549));
        territoryArea.add(new SimpleLocation(37.2327997, -5.9006107));
        territoryArea.add(new SimpleLocation(37.233533, -5.9004698));
        territoryArea.add(new SimpleLocation(37.2340429, -5.9002526));
        territoryArea.add(new SimpleLocation(37.2350486, -5.900032));
        territoryArea.add(new SimpleLocation(37.2358798, -5.8999398));
        territoryArea.add(new SimpleLocation(37.2370758, -5.8996701));
        territoryArea.add(new SimpleLocation(37.2424546, -5.8978587));
        territoryArea.add(new SimpleLocation(37.2446166, -5.8967806));
        territoryArea.add(new SimpleLocation(37.2453617, -5.8965273));
        territoryArea.add(new SimpleLocation(37.2463018, -5.8964055));
        territoryArea.add(new SimpleLocation(37.2479049, -5.8960724));
        territoryArea.add(new SimpleLocation(37.2487768, -5.8957901));
        territoryArea.add(new SimpleLocation(37.2496646, -5.8955987));
        territoryArea.add(new SimpleLocation(37.251126, -5.8955306));
        territoryArea.add(new SimpleLocation(37.2517193, -5.8955871));
        territoryArea.add(new SimpleLocation(37.2536648, -5.8963605));
        territoryArea.add(new SimpleLocation(37.2549792, -5.8971436));
        territoryArea.add(new SimpleLocation(37.2559178, -5.8978221));
        territoryArea.add(new SimpleLocation(37.256819, -5.8970788));
        territoryArea.add(new SimpleLocation(37.2576711, -5.8965027));
        territoryArea.add(new SimpleLocation(37.2581404, -5.8961035));
        territoryArea.add(new SimpleLocation(37.2591321, -5.8949803));
        territoryArea.add(new SimpleLocation(37.2599654, -5.8944373));
        territoryArea.add(new SimpleLocation(37.2600609, -5.8942155));
        territoryArea.add(new SimpleLocation(37.260054, -5.8933922));
        territoryArea.add(new SimpleLocation(37.2606502, -5.8925921));
        territoryArea.add(new SimpleLocation(37.2611376, -5.8932984));
        territoryArea.add(new SimpleLocation(37.2616675, -5.8937358));
        territoryArea.add(new SimpleLocation(37.2631759, -5.8946955));
        territoryArea.add(new SimpleLocation(37.2649404, -5.8936581));
        territoryArea.add(new SimpleLocation(37.2658436, -5.8935687));
        territoryArea.add(new SimpleLocation(37.2668519, -5.8936073));
        territoryArea.add(new SimpleLocation(37.2670009, -5.89341));
        territoryArea.add(new SimpleLocation(37.2674272, -5.8925582));
        territoryArea.add(new SimpleLocation(37.2678565, -5.8919545));
        territoryArea.add(new SimpleLocation(37.2689881, -5.8906335));
        territoryArea.add(new SimpleLocation(37.2715354, -5.8876404));
        territoryArea.add(new SimpleLocation(37.2719765, -5.887072));
        territoryArea.add(new SimpleLocation(37.2722163, -5.8868444));
        territoryArea.add(new SimpleLocation(37.2731615, -5.8861476));
        territoryArea.add(new SimpleLocation(37.2749511, -5.8844456));
        territoryArea.add(new SimpleLocation(37.2762219, -5.8833214));
        territoryArea.add(new SimpleLocation(37.2768006, -5.883242));
        territoryArea.add(new SimpleLocation(37.2770761, -5.8833991));
        territoryArea.add(new SimpleLocation(37.277656, -5.8840076));
        territoryArea.add(new SimpleLocation(37.2781717, -5.8846588));
        territoryArea.add(new SimpleLocation(37.2786239, -5.88533));
        territoryArea.add(new SimpleLocation(37.2798841, -5.8875996));
        territoryArea.add(new SimpleLocation(37.2807538, -5.8892567));
        territoryArea.add(new SimpleLocation(37.2819, -5.8910259));
        territoryArea.add(new SimpleLocation(37.2827983, -5.8922443));
        territoryArea.add(new SimpleLocation(37.283919, -5.8935841));
        territoryArea.add(new SimpleLocation(37.2847701, -5.8945189));
        territoryArea.add(new SimpleLocation(37.2852218, -5.8948407));
        territoryArea.add(new SimpleLocation(37.2868528, -5.8952076));
        territoryArea.add(new SimpleLocation(37.2881338, -5.8955161));
        territoryArea.add(new SimpleLocation(37.2885069, -5.8957334));
        territoryArea.add(new SimpleLocation(37.289719, -5.8966483));
        territoryArea.add(new SimpleLocation(37.2903539, -5.897214));
        territoryArea.add(new SimpleLocation(37.2909064, -5.8978329));
        territoryArea.add(new SimpleLocation(37.2919281, -5.89953));
        territoryArea.add(new SimpleLocation(37.2925009, -5.9007926));
        territoryArea.add(new SimpleLocation(37.2935345, -5.9009));
        territoryArea.add(new SimpleLocation(37.2940061, -5.9011436));
        territoryArea.add(new SimpleLocation(37.2946446, -5.9015629));
        territoryArea.add(new SimpleLocation(37.2960057, -5.9030138));
        territoryArea.add(new SimpleLocation(37.2966854, -5.9039535));
        territoryArea.add(new SimpleLocation(37.2979348, -5.9055468));
        territoryArea.add(new SimpleLocation(37.2981671, -5.9059956));
        territoryArea.add(new SimpleLocation(37.2988175, -5.9059304));
        territoryArea.add(new SimpleLocation(37.2990558, -5.9068645));
        territoryArea.add(new SimpleLocation(37.2991752, -5.9086061));
        territoryArea.add(new SimpleLocation(37.2995559, -5.9110797));
        territoryArea.add(new SimpleLocation(37.2999249, -5.9125603));
        territoryArea.add(new SimpleLocation(37.3005481, -5.9143326));
        territoryArea.add(new SimpleLocation(37.3010565, -5.9152772));
        territoryArea.add(new SimpleLocation(37.3013943, -5.9161926));
        territoryArea.add(new SimpleLocation(37.302915, -5.9166461));
        territoryArea.add(new SimpleLocation(37.3056756, -5.917971));
        territoryArea.add(new SimpleLocation(37.3067141, -5.918609));
        territoryArea.add(new SimpleLocation(37.307341, -5.9191296));
        territoryArea.add(new SimpleLocation(37.3082805, -5.9186696));
        territoryArea.add(new SimpleLocation(37.308404, -5.9169484));
        territoryArea.add(new SimpleLocation(37.3086039, -5.9157829));
        territoryArea.add(new SimpleLocation(37.3090959, -5.915565));
        territoryArea.add(new SimpleLocation(37.3100128, -5.9149236));
        territoryArea.add(new SimpleLocation(37.311181, -5.9139534));
        territoryArea.add(new SimpleLocation(37.3117382, -5.9136477));
        territoryArea.add(new SimpleLocation(37.3128582, -5.913172));
        territoryArea.add(new SimpleLocation(37.313675, -5.9125717));
        territoryArea.add(new SimpleLocation(37.3151034, -5.9116453));
        territoryArea.add(new SimpleLocation(37.3162141, -5.9108195));
        territoryArea.add(new SimpleLocation(37.3178072, -5.9094255));
        territoryArea.add(new SimpleLocation(37.3186239, -5.9088252));
        territoryArea.add(new SimpleLocation(37.3199058, -5.9079946));
        territoryArea.add(new SimpleLocation(37.3209659, -5.9073923));
        territoryArea.add(new SimpleLocation(37.3239366, -5.9060399));
        territoryArea.add(new SimpleLocation(37.3243765, -5.905741));
        territoryArea.add(new SimpleLocation(37.3251768, -5.9050722));
        territoryArea.add(new SimpleLocation(37.3257793, -5.9047569));
        territoryArea.add(new SimpleLocation(37.3271148, -5.9043118));
        territoryArea.add(new SimpleLocation(37.3276293, -5.9042752));
        territoryArea.add(new SimpleLocation(37.3286047, -5.9038163));
        territoryArea.add(new SimpleLocation(37.3291239, -5.9043215));
        territoryArea.add(new SimpleLocation(37.3294994, -5.9048099));
        territoryArea.add(new SimpleLocation(37.3310029, -5.9052288));
        territoryArea.add(new SimpleLocation(37.3314796, -5.9056309));
        territoryArea.add(new SimpleLocation(37.3334276, -5.9077597));
        territoryArea.add(new SimpleLocation(37.333978, -5.9080969));
        territoryArea.add(new SimpleLocation(37.3355846, -5.9098177));
        territoryArea.add(new SimpleLocation(37.3365379, -5.9113554));
        territoryArea.add(new SimpleLocation(37.3368694, -5.911797));
        territoryArea.add(new SimpleLocation(37.3384145, -5.9130867));
        territoryArea.add(new SimpleLocation(37.3390528, -5.9138788));
        territoryArea.add(new SimpleLocation(37.3397792, -5.9151144));
        territoryArea.add(new SimpleLocation(37.3402435, -5.9156515));
        territoryArea.add(new SimpleLocation(37.3469717, -5.9223337));
        territoryArea.add(new SimpleLocation(37.3480449, -5.9226574));
        territoryArea.add(new SimpleLocation(37.3487216, -5.9229884));
        territoryArea.add(new SimpleLocation(37.3498197, -5.9237646));
        territoryArea.add(new SimpleLocation(37.3509319, -5.9246995));
        territoryArea.add(new SimpleLocation(37.3520114, -5.9254976));
        territoryArea.add(new SimpleLocation(37.353902, -5.9266546));
        territoryArea.add(new SimpleLocation(37.354552, -5.9273345));
        territoryArea.add(new SimpleLocation(37.3612181, -5.9372113));
        territoryArea.add(new SimpleLocation(37.362076, -5.9385881));
        territoryArea.add(new SimpleLocation(37.3607136, -5.9390204));
        territoryArea.add(new SimpleLocation(37.3572478, -5.9403414));
        territoryArea.add(new SimpleLocation(37.3551434, -5.9416251));
        territoryArea.add(new SimpleLocation(37.3538354, -5.9425211));
        territoryArea.add(new SimpleLocation(37.3530185, -5.9430808));
        territoryArea.add(new SimpleLocation(37.3528749, -5.9431782));
        territoryArea.add(new SimpleLocation(37.3523842, -5.9435153));
        territoryArea.add(new SimpleLocation(37.3456269, -5.9492783));
        territoryArea.add(new SimpleLocation(37.3435067, -5.9504707));
        territoryArea.add(new SimpleLocation(37.3393653, -5.9532203));
        territoryArea.add(new SimpleLocation(37.3367399, -5.9551601));
        territoryArea.add(new SimpleLocation(37.3366617, -5.9554053));
        territoryArea.add(new SimpleLocation(37.3363269, -5.955821));
        territoryArea.add(new SimpleLocation(37.3348694, -5.9571744));
        territoryArea.add(new SimpleLocation(37.3342432, -5.957353));
        territoryArea.add(new SimpleLocation(37.3334769, -5.9573681));
        territoryArea.add(new SimpleLocation(37.3328451, -5.957411));
        territoryArea.add(new SimpleLocation(37.3318302, -5.957642));
        territoryArea.add(new SimpleLocation(37.329569, -5.9583433));
        territoryArea.add(new SimpleLocation(37.3288216, -5.9586863));
        territoryArea.add(new SimpleLocation(37.3284646, -5.9589093));
        territoryArea.add(new SimpleLocation(37.3262329, -5.9605932));
        territoryArea.add(new SimpleLocation(37.3232051, -5.9641978));
        territoryArea.add(new SimpleLocation(37.3220325, -5.9642205));
        territoryArea.add(new SimpleLocation(37.3205991, -5.9644351));
        territoryArea.add(new SimpleLocation(37.3194899, -5.9648428));
        territoryArea.add(new SimpleLocation(37.3186157, -5.9653948));
        territoryArea.add(new SimpleLocation(37.3183125, -5.9655723));
        territoryArea.add(new SimpleLocation(37.3167194, -5.9666394));
        territoryArea.add(new SimpleLocation(37.3152295, -5.9674833));
        territoryArea.add(new SimpleLocation(37.3130011, -5.9690315));
        territoryArea.add(new SimpleLocation(37.3131648, -5.9700612));
        territoryArea.add(new SimpleLocation(37.3134947, -5.9720468));
        territoryArea.add(new SimpleLocation(37.3145926, -5.9759758));
        territoryArea.add(new SimpleLocation(37.3149033, -5.977951));
        territoryArea.add(new SimpleLocation(37.3150189, -5.9790837));
        territoryArea.add(new SimpleLocation(37.3152691, -5.9798833));
        territoryArea.add(new SimpleLocation(37.3160332, -5.9828129));
        territoryArea.add(new SimpleLocation(37.3162526, -5.9841189));
        territoryArea.add(new SimpleLocation(37.3165948, -5.9855539));
        territoryArea.add(new SimpleLocation(37.3169269, -5.9877444));
        territoryArea.add(new SimpleLocation(37.3171503, -5.9885317));
        territoryArea.add(new SimpleLocation(37.3177047, -5.9911936));
        territoryArea.add(new SimpleLocation(37.3177601, -5.991554));
        territoryArea.add(new SimpleLocation(37.3179523, -5.9928055));
        territoryArea.add(new SimpleLocation(37.3182844, -5.9939243));
        territoryArea.add(new SimpleLocation(37.3190779, -5.9978142));
        territoryArea.add(new SimpleLocation(37.3193329, -5.9994828));
        territoryArea.add(new SimpleLocation(37.3203047, -6.0030866));
        territoryArea.add(new SimpleLocation(37.3197647, -6.0034149));
        territoryArea.add(new SimpleLocation(37.3194605, -6.0036848));
        territoryArea.add(new SimpleLocation(37.3190797, -6.0041322));
        territoryArea.add(new SimpleLocation(37.3185353, -6.0049905));
        territoryArea.add(new SimpleLocation(37.3180642, -6.0061564));
        territoryArea.add(new SimpleLocation(37.3175174, -6.0078156));
        territoryArea.add(new SimpleLocation(37.3170938, -6.0088817));
        territoryArea.add(new SimpleLocation(37.3153388, -6.0126926));
        territoryArea.add(new SimpleLocation(37.3148549, -6.0140045));
        territoryArea.add(new SimpleLocation(37.3144982, -6.0145656));
        territoryArea.add(new SimpleLocation(37.3135408, -6.0164226));
        territoryArea.add(new SimpleLocation(37.3130027, -6.0173825));
        territoryArea.add(new SimpleLocation(37.3126466, -6.0179211));
        territoryArea.add(new SimpleLocation(37.3120889, -6.0185869));
        territoryArea.add(new SimpleLocation(37.3097483, -6.020975));
        territoryArea.add(new SimpleLocation(37.309051, -6.0218157));
        territoryArea.add(new SimpleLocation(37.3085898, -6.0222372));
        territoryArea.add(new SimpleLocation(37.3078643, -6.0227721));
        territoryArea.add(new SimpleLocation(37.3066089, -6.0235904));
        territoryArea.add(new SimpleLocation(37.305177, -6.024616));
        territoryArea.add(new SimpleLocation(37.3040591, -6.0256992));
        territoryArea.add(new SimpleLocation(37.3029388, -6.0268724));
        territoryArea.add(new SimpleLocation(37.3017982, -6.0284848));
        territoryArea.add(new SimpleLocation(37.300874, -6.0300945));
        territoryArea.add(new SimpleLocation(37.3003269, -6.0317531));
        territoryArea.add(new SimpleLocation(37.3002035, -6.0326955));
        territoryArea.add(new SimpleLocation(37.2997389, -6.0350003));
        territoryArea.add(new SimpleLocation(37.2995683, -6.0360311));
        territoryArea.add(new SimpleLocation(37.2988388, -6.0377726));
        territoryArea.add(new SimpleLocation(37.2976625, -6.0402649));

        locations.add(territoryArea);

        return new TerritoryArea(DOS_HERMANAS, locations);
    }

    // 117 points
    private TerritoryArea generateAlfalfa() {
        List<List<SimpleLocation>> locations = new ArrayList<>();
        List<SimpleLocation> territoryArea = new ArrayList<>();

        territoryArea.add(new SimpleLocation(37.3909594, -5.9986752));
        territoryArea.add(new SimpleLocation(37.3909191, -5.9984664));
        territoryArea.add(new SimpleLocation(37.3907122, -5.9980842));
        territoryArea.add(new SimpleLocation(37.3906312, -5.9979558));
        territoryArea.add(new SimpleLocation(37.3905795, -5.9978981));
        territoryArea.add(new SimpleLocation(37.3905088, -5.9978083));
        territoryArea.add(new SimpleLocation(37.3904554, -5.9978926));
        territoryArea.add(new SimpleLocation(37.3903271, -5.9977966));
        territoryArea.add(new SimpleLocation(37.3899168, -5.9975073));
        territoryArea.add(new SimpleLocation(37.3897905, -5.997705));
        territoryArea.add(new SimpleLocation(37.3895651, -5.9974055));
        territoryArea.add(new SimpleLocation(37.3893279, -5.9971806));
        territoryArea.add(new SimpleLocation(37.3892254, -5.9973496));
        territoryArea.add(new SimpleLocation(37.3888899, -5.9970754));
        territoryArea.add(new SimpleLocation(37.3888971, -5.9969581));
        territoryArea.add(new SimpleLocation(37.3889305, -5.9964037));
        territoryArea.add(new SimpleLocation(37.3889892, -5.9956225));
        territoryArea.add(new SimpleLocation(37.3890413, -5.9949282));
        territoryArea.add(new SimpleLocation(37.3882854, -5.9948407));
        territoryArea.add(new SimpleLocation(37.3880595, -5.9944518));
        territoryArea.add(new SimpleLocation(37.3883305, -5.9941772));
        territoryArea.add(new SimpleLocation(37.3889609, -5.9942179));
        territoryArea.add(new SimpleLocation(37.3891401, -5.9940691));
        territoryArea.add(new SimpleLocation(37.3891608, -5.994052));
        territoryArea.add(new SimpleLocation(37.3893292, -5.9938597));
        territoryArea.add(new SimpleLocation(37.3893646, -5.9937871));
        territoryArea.add(new SimpleLocation(37.3893547, -5.9934849));
        territoryArea.add(new SimpleLocation(37.3893543, -5.9934427));
        territoryArea.add(new SimpleLocation(37.3893703, -5.9933745));
        territoryArea.add(new SimpleLocation(37.3894031, -5.9933224));
        territoryArea.add(new SimpleLocation(37.3894527, -5.9932897));
        territoryArea.add(new SimpleLocation(37.3896574, -5.9932245));
        territoryArea.add(new SimpleLocation(37.3894138, -5.9925585));
        territoryArea.add(new SimpleLocation(37.389179, -5.9925694));
        territoryArea.add(new SimpleLocation(37.3890468, -5.9925749));
        territoryArea.add(new SimpleLocation(37.3889063, -5.9926008));
        territoryArea.add(new SimpleLocation(37.3887721, -5.9926409));
        territoryArea.add(new SimpleLocation(37.3886888, -5.992497));
        territoryArea.add(new SimpleLocation(37.388527, -5.9923171));
        territoryArea.add(new SimpleLocation(37.3884582, -5.992209));
        territoryArea.add(new SimpleLocation(37.3884621, -5.9918208));
        territoryArea.add(new SimpleLocation(37.3884273, -5.9915105));
        territoryArea.add(new SimpleLocation(37.3883795, -5.9914983));
        territoryArea.add(new SimpleLocation(37.3883355, -5.9911863));
        territoryArea.add(new SimpleLocation(37.3883375, -5.9911469));
        territoryArea.add(new SimpleLocation(37.3883576, -5.991128));
        territoryArea.add(new SimpleLocation(37.3884328, -5.9911197));
        territoryArea.add(new SimpleLocation(37.3884674, -5.9911036));
        territoryArea.add(new SimpleLocation(37.3885297, -5.9910101));
        territoryArea.add(new SimpleLocation(37.3886094, -5.9908414));
        territoryArea.add(new SimpleLocation(37.388688, -5.9906825));
        territoryArea.add(new SimpleLocation(37.3887085, -5.9906411));
        territoryArea.add(new SimpleLocation(37.3887232, -5.9905716));
        territoryArea.add(new SimpleLocation(37.3888056, -5.9905402));
        territoryArea.add(new SimpleLocation(37.388845, -5.9904864));
        territoryArea.add(new SimpleLocation(37.3890756, -5.990375));
        territoryArea.add(new SimpleLocation(37.3891482, -5.9903162));
        territoryArea.add(new SimpleLocation(37.3893074, -5.9901826));
        territoryArea.add(new SimpleLocation(37.3894176, -5.9900685));
        territoryArea.add(new SimpleLocation(37.3895002, -5.9896616));
        territoryArea.add(new SimpleLocation(37.3894935, -5.9894369));
        territoryArea.add(new SimpleLocation(37.3895289, -5.9892719));
        territoryArea.add(new SimpleLocation(37.3895872, -5.9891843));
        territoryArea.add(new SimpleLocation(37.389749, -5.9890298));
        territoryArea.add(new SimpleLocation(37.3898372, -5.9889854));
        territoryArea.add(new SimpleLocation(37.3898401, -5.9889545));
        territoryArea.add(new SimpleLocation(37.3898592, -5.9887401));
        territoryArea.add(new SimpleLocation(37.3899179, -5.9884262));
        territoryArea.add(new SimpleLocation(37.3900321, -5.9884582));
        territoryArea.add(new SimpleLocation(37.3902612, -5.9883272));
        territoryArea.add(new SimpleLocation(37.3905701, -5.9882679));
        territoryArea.add(new SimpleLocation(37.3905909, -5.9883783));
        territoryArea.add(new SimpleLocation(37.3907268, -5.9885585));
        territoryArea.add(new SimpleLocation(37.3909828, -5.9889685));
        territoryArea.add(new SimpleLocation(37.3909862, -5.988974));
        territoryArea.add(new SimpleLocation(37.3911613, -5.9888216));
        territoryArea.add(new SimpleLocation(37.3912444, -5.9888218));
        territoryArea.add(new SimpleLocation(37.3914636, -5.988791));
        territoryArea.add(new SimpleLocation(37.3918767, -5.9886694));
        territoryArea.add(new SimpleLocation(37.3922897, -5.9885963));
        territoryArea.add(new SimpleLocation(37.3928255, -5.9885105));
        territoryArea.add(new SimpleLocation(37.3928322, -5.9886638));
        territoryArea.add(new SimpleLocation(37.3925271, -5.9895526));
        territoryArea.add(new SimpleLocation(37.3924798, -5.9898717));
        territoryArea.add(new SimpleLocation(37.3924891, -5.990068));
        territoryArea.add(new SimpleLocation(37.3924994, -5.9902279));
        territoryArea.add(new SimpleLocation(37.3925454, -5.9903786));
        territoryArea.add(new SimpleLocation(37.3925609, -5.990465));
        territoryArea.add(new SimpleLocation(37.392593, -5.9906681));
        territoryArea.add(new SimpleLocation(37.3926929, -5.9913641));
        territoryArea.add(new SimpleLocation(37.3926952, -5.9913799));
        territoryArea.add(new SimpleLocation(37.3927905, -5.9923054));
        territoryArea.add(new SimpleLocation(37.3927908, -5.9930081));
        territoryArea.add(new SimpleLocation(37.3928186, -5.9936394));
        territoryArea.add(new SimpleLocation(37.392805, -5.9939443));
        territoryArea.add(new SimpleLocation(37.3927631, -5.994718));
        territoryArea.add(new SimpleLocation(37.3927572, -5.9948092));
        territoryArea.add(new SimpleLocation(37.3927557, -5.9948315));
        territoryArea.add(new SimpleLocation(37.3927388, -5.9950901));
        territoryArea.add(new SimpleLocation(37.3927178, -5.9954119));
        territoryArea.add(new SimpleLocation(37.3927126, -5.9954922));
        territoryArea.add(new SimpleLocation(37.392716, -5.9956412));
        territoryArea.add(new SimpleLocation(37.3927188, -5.9957771));
        territoryArea.add(new SimpleLocation(37.3927264, -5.9961446));
        territoryArea.add(new SimpleLocation(37.392705, -5.9962233));
        territoryArea.add(new SimpleLocation(37.3927975, -5.9969266));
        territoryArea.add(new SimpleLocation(37.3922325, -5.9969101));
        territoryArea.add(new SimpleLocation(37.3922313, -5.997517));
        territoryArea.add(new SimpleLocation(37.39229, -5.9981454));
        territoryArea.add(new SimpleLocation(37.3923198, -5.9982969));
        territoryArea.add(new SimpleLocation(37.3919897, -5.9983037));
        territoryArea.add(new SimpleLocation(37.3916673, -5.9981923));
        territoryArea.add(new SimpleLocation(37.3914817, -5.9980612));
        territoryArea.add(new SimpleLocation(37.3913137, -5.997951));
        territoryArea.add(new SimpleLocation(37.3911295, -5.9982904));
        territoryArea.add(new SimpleLocation(37.3909943, -5.9985046));
        territoryArea.add(new SimpleLocation(37.3909594, -5.9986752));

        locations.add(territoryArea);

        return new TerritoryArea(ALFALFA, locations);
    }

    // 117 points
    private TerritoryArea generateWorld() {
        List<List<SimpleLocation>> locations = new ArrayList<>();

        return new TerritoryArea(WORLD, locations);
    }

    private Territory getMontequinto() {
        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey(MONTEQUINTO)
                .withParentKey(DOS_HERMANAS)
                .withName(MONTEQUINTO)
                .withParentName(DOS_HERMANAS)
                .withArea(148500)
                .withTotalFootprints(18900)
                .withCountryEmoji("ES")
                .withHasSons(false)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getDosHermanas() {
        final User user = generateUsers.generateUser2(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey(DOS_HERMANAS)
                .withParentKey(ALFALFA)
                .withName(DOS_HERMANAS)
                .withParentName(ALFALFA)
                .withArea(8500)
                .withTotalFootprints(300)
                .withCountryEmoji("ES")
                .withHasSons(true)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getAlfalfa() {
        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey(ALFALFA)
                .withParentKey(WORLD)
                .withName(ALFALFA)
                .withParentName(WORLD)
                .withArea(8500)
                .withTotalFootprints(300)
                .withCountryEmoji("ES")
                .withHasSons(true)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getWorld() {
        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey(WORLD)
                .withParentKey("")
                .withName(WORLD)
                .withParentName("")
                .withArea(8500)
                .withTotalFootprints(300)
                .withCountryEmoji(EMOJI_WORLD)
                .withHasSons(true)
                .withLeader(user)
                .build();

        return territory;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
