package com.streams;

import com.github.DownloadGitHubFiles;
import com.github.dto.CropInsuranceDTO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class MapExamples {

    public static void main(String[] args) {
        try {
            DownloadGitHubFiles.downloadFile("csv/crop_insurance.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String cropFileLocation = "D:/DataFiles/Downloaded/csv/crop_insurance.csv";
        List<CropInsuranceDTO> cropList = getCropDetails(cropFileLocation);
        cropList.add(null);
        cropList.add(new CropInsuranceDTO());

        //groupByMandals(cropList);
        //groupByMandalStats(cropList);
        //groupBySumOfClaims(cropList);
        //groupByMinClaimOfEachMandal(cropList);
        //groupByMaxClaimOfEachMandal(cropList);
        //groupByAvgClaimOfEachMandal(cropList);
        //groupByCountClaimOfEachMandal(cropList);
        //groupByMadalMax10Claims(cropList);
        //groupByMadalMin10Claims(cropList);
        //groupByMadalMaxDist10Claims(cropList);
        //groupByMadalMinDist10Claims(cropList);
        //groupByMandalClaimBetween(cropList);
        //groupByMandalClaimBetweenDetail(cropList);
        //groupByMandalClaimGreaterThan(cropList);
        //17 21
        //groupByVillCount(cropList);
        sortMapValues();
        sortMapValuesReverseOrder();
        getKeyOrValuesFromMap();
    }

    private static void getKeyOrValuesFromMap() {
        Map<String, Integer> map = Map.of("a", 2,
                "w", 33,
                "f", 12,
                "t", 5);
        List<Integer> valueList =
                map.entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .toList();
        List<String> keyList =
                map.entrySet()
                        .stream()
                        .map(Map.Entry::getKey)
                        .toList();
    }

    private static void sortMapValuesReverseOrder() {
        Map<String, Integer> map = Map.of("a", 2,
                "w", 33,
                "f", 12,
                "t", 5);
        LinkedHashMap<String, Integer> linkedHashMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (old, newVal) -> old, LinkedHashMap::new));
    }

    private static void sortMapValues() {
        Map<String, Integer> map = Map.of("a", 2,
                "w", 33,
                "f", 12,
                "t", 5);
        LinkedHashMap<String, Integer> linkedHashMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (old, newVal) -> old, LinkedHashMap::new));
    }

    private static void groupByVillCount(List<CropInsuranceDTO> cropList) {
        Map<String, Long> groupByVillCounting = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getVillageName() != null)
                .collect(groupingBy(CropInsuranceDTO::getVillageName, counting()));
        groupByVillCounting.forEach((k, v) -> System.out.println(k + "====" + v));
    }

    private static void groupByMandalClaimGreaterThan(List<CropInsuranceDTO> cropList) {
        Map<String, List<CropInsuranceDTO>> groupByTop10 =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .filter(f -> f.getMandalName() != null)
                        .collect(groupingBy(CropInsuranceDTO::getMandalName,
                                collectingAndThen(toList(), l -> l.parallelStream()
                                        .sorted(comparing(CropInsuranceDTO::getClaimAmountRs).reversed())
                                        .filter(f -> f.getClaimAmountRs() >= 50000)
                                        .collect(toList()))));
        groupByTop10.forEach((k, v) -> {
            System.out.println("Key is===" + k);
            v.forEach(f -> System.out.println(f.getClaimAmountRs()));
        });
    }

    private static void groupByMandalClaimBetweenDetail(List<CropInsuranceDTO> cropList) {
        Map<String, List<VillageAndName>> claimBetweenDetail =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .filter(f -> f.getMandalName() != null)
                        .filter(f -> f.getClaimAmountRs() >= 80000 && f.getClaimAmountRs() <= 100000)
                        .collect(groupingBy(CropInsuranceDTO::getMandalName,
                                collectingAndThen(toList(), l -> l.stream()
                                        .map(m -> {
                                            VillageAndName vill = new VillageAndName();
                                            vill.setClaim(m.getClaimAmountRs());
                                            vill.setName(m.getNameOfTheBeneficiary());
                                            vill.setVillage(m.getVillageName());
                                            return vill;
                                        })
                                        .sorted(comparing(VillageAndName::getClaim).reversed())
                                        .collect(toList()))));
        claimBetweenDetail.forEach((k, v) -> {
            System.out.println("Mandal is ==" + k);
            v.forEach(f -> {
                System.out.println(f.getClaim());
            });
        });
    }

    private static void groupByMandalClaimBetween(List<CropInsuranceDTO> cropList) {
        Map<String, List<CropInsuranceDTO>> claimBetween = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getClaimAmountRs() >= 80000 && f.getClaimAmountRs() <= 100000)
                .collect(groupingBy(CropInsuranceDTO::getMandalName, toList()));
        claimBetween.forEach((k, v) -> {
            System.out.println("===Mandal==" + k);
            v.forEach(f -> System.out.println(f.getClaimAmountRs()));
        });
    }

    private static void groupByMadalMinDist10Claims(List<CropInsuranceDTO> cropList) {
        Map<String, List<Double>> groupByTop10 =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .filter(f -> f.getMandalName() != null)
                        .collect(groupingBy(CropInsuranceDTO::getMandalName,
                                collectingAndThen(toList(), l -> l.stream()
                                        .map(CropInsuranceDTO::getClaimAmountRs)
                                        .sorted()
                                        .distinct()
                                        .limit(10)
                                        .collect(toList()))));
        groupByTop10.forEach((k, v) -> {
            System.out.println("Key is===" + k);
            v.forEach(f -> System.out.println(f));
        });
    }

    private static void groupByMadalMaxDist10Claims(List<CropInsuranceDTO> cropList) {
        Map<String, List<Double>> groupByTop10 =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .filter(f -> f.getMandalName() != null)
                        .collect(groupingBy(CropInsuranceDTO::getMandalName,
                                collectingAndThen(toList(), l -> l.stream()
                                        .map(CropInsuranceDTO::getClaimAmountRs)
                                        .sorted(reverseOrder())
                                        .distinct()
                                        .limit(10)
                                        .collect(toList()))));
        groupByTop10.forEach((k, v) -> {
            System.out.println("Key is===" + k);
            v.forEach(f -> System.out.println(f));
        });
    }

    private static void groupByMadalMin10Claims(List<CropInsuranceDTO> cropList) {
        Map<String, List<CropInsuranceDTO>> groupByTop10 =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .filter(f -> f.getMandalName() != null)
                        .collect(groupingBy(CropInsuranceDTO::getMandalName,
                                collectingAndThen(toList(), l -> l.stream()
                                        .sorted(comparing(CropInsuranceDTO::getClaimAmountRs))
                                        .limit(10).collect(toList()))));
        groupByTop10.forEach((k, v) -> {
            System.out.println("Key is===" + k);
            v.forEach(f -> System.out.println(f.getClaimAmountRs()));
        });
    }

    private static void groupByMadalMax10Claims(List<CropInsuranceDTO> cropList) {
        Map<String, List<CropInsuranceDTO>> groupByTop10 =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .filter(f -> f.getMandalName() != null)
                        .collect(groupingBy(CropInsuranceDTO::getMandalName,
                                collectingAndThen(toList(), l -> l.stream()
                                        .sorted(comparing(CropInsuranceDTO::getClaimAmountRs).reversed())
                                        .limit(10).collect(toList()))));
        groupByTop10.forEach((k, v) -> {
            System.out.println("Key is===" + k);
            v.forEach(f -> System.out.println(f.getClaimAmountRs()));
        });
    }

    private static void groupByAvgClaimOfEachMandal(List<CropInsuranceDTO> cropList) {
        Map<String, Double> avgClaimByMandal = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(f -> f.getMandalName() != null)
                .collect(groupingBy(CropInsuranceDTO::getMandalName, averagingDouble(CropInsuranceDTO::getClaimAmountRs)));
        avgClaimByMandal.forEach((k, v) -> {
            System.out.println(k + "====" + v);
        });
    }

    private static void groupByMaxClaimOfEachMandal(List<CropInsuranceDTO> cropList) {
        Map<String, Optional<CropInsuranceDTO>> maxClaimByMandal = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getMandalName() != null)
                .collect(groupingBy(CropInsuranceDTO::getMandalName,
                        maxBy(comparingDouble(CropInsuranceDTO::getClaimAmountRs))));
        maxClaimByMandal.forEach((k, v) -> {
            System.out.println(k + "====" + v.get().getClaimAmountRs());
        });
    }

    private static void groupByMinClaimOfEachMandal(List<CropInsuranceDTO> cropList) {
        Map<String, Optional<CropInsuranceDTO>> minClaimByMandal = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getMandalName() != null)
                .collect(groupingBy(CropInsuranceDTO::getMandalName,
                        minBy(comparingDouble(CropInsuranceDTO::getClaimAmountRs))));
        minClaimByMandal.forEach((k, v) -> {
            System.out.println(k + "====" + v.get().getClaimAmountRs());
        });
    }

    private static void groupBySumOfClaims(List<CropInsuranceDTO> cropList) {
        Map<String, Double> sumOfDept = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getMandalName() != null)
                .collect(groupingBy(CropInsuranceDTO::getMandalName, summingDouble(CropInsuranceDTO::getClaimAmountRs)));
        sumOfDept.forEach((k, v) -> {
            System.out.println(k + "====" + v);
        });
    }

    private static void groupByMandalStats(List<CropInsuranceDTO> cropList) {
        Map<String, DoubleSummaryStatistics> byMandalStats = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getMandalName() != null)
                .collect(groupingBy(CropInsuranceDTO::getMandalName,
                        summarizingDouble(CropInsuranceDTO::getClaimAmountRs)));
        byMandalStats.forEach((k, v) -> {
            System.out.println(k + "===" + v.getMax() + "==min==" + v.getMin());
        });
    }

    private static void groupByMandals(List<CropInsuranceDTO> cropList) {
        Map<String, List<CropInsuranceDTO>> byMandal =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .filter(f -> f.getMandalName() != null)
                        .collect(groupingBy(CropInsuranceDTO::getMandalName, toList()));

        byMandal.forEach((k, v) -> {
            System.out.println(k + "===" + v.size());
        });
    }

    private static List<CropInsuranceDTO> getCropDetails(String fileLocation) {
        List<CropInsuranceDTO> listCrop = null;
        try {
            listCrop = new CsvToBeanBuilder(new FileReader(fileLocation))
                    .withType(CropInsuranceDTO.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
