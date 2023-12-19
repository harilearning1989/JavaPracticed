package com.streams;

import com.github.DownloadGitHubFiles;
import com.github.dto.CropInsuranceDTO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class CropParallelStream {
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

        //findTheMaxClaim(cropList);
        //findTheMinClaim(cropList);
        //countTheCropsByMandal(cropList);
        //averageClaimAmount(cropList);
        //totalClaimAmount(cropList);

        //claimSummaryStats(cropList);

        //findMaxClaimRow(cropList);
        //findMinClaimRow(cropList);

        //getDistinctMandals(cropList);
        //getVillageAndName(cropList);
        //getAllCropsMoreThanLakh(cropList);
        //getAllCropsMoreThanLakhCount(cropList);

        //filterAndSort(cropList);
        //filterAndSortReverse(cropList);
        //filterAndSortIgnoreCase(cropList);
        //filterAndSortNullsLast(cropList);
    }

    private static void filterAndSortNullsLast(List<CropInsuranceDTO> cropList) {
        List<CropInsuranceDTO> cropData = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getClaimAmountRs() >= 100000)
                .sorted(comparing(CropInsuranceDTO::getNameOfTheBeneficiary, nullsLast(String::compareToIgnoreCase)))
                .toList();
        cropData.forEach(f -> System.out.println(f.getNameOfTheBeneficiary()));
    }

    private static void filterAndSortIgnoreCase(List<CropInsuranceDTO> cropList) {
        List<CropInsuranceDTO> cropData = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getClaimAmountRs() >= 100000)
                .sorted(comparing(CropInsuranceDTO::getNameOfTheBeneficiary, String::compareToIgnoreCase))
                .toList();
        //.collect(toList());
        cropData.forEach(f -> System.out.println(f.getNameOfTheBeneficiary()));
    }

    private static void filterAndSortReverse(List<CropInsuranceDTO> cropList) {
        List<CropInsuranceDTO> cropData = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getClaimAmountRs() >= 100000)
                .sorted(comparing(CropInsuranceDTO::getNameOfTheBeneficiary).reversed())
                .toList();
        cropData.forEach(f -> System.out.println(f.getNameOfTheBeneficiary()));
    }

    private static void filterAndSort(List<CropInsuranceDTO> cropList) {
        List<CropInsuranceDTO> cropData = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getClaimAmountRs() >= 100000)
                .sorted(comparing(CropInsuranceDTO::getNameOfTheBeneficiary))
                .toList();
        cropData.forEach(f -> System.out.println(f.getNameOfTheBeneficiary()));
    }

    private static void getAllCropsMoreThanLakhCount(List<CropInsuranceDTO> cropList) {
        long count = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getClaimAmountRs() >= 100000)
                .count();
        System.out.println("More Than Lakhs Claims==" + count);
    }

    private static void getAllCropsMoreThanLakh(List<CropInsuranceDTO> cropList) {
        List<CropInsuranceDTO> cropData = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getClaimAmountRs() >= 100000)
                .toList();
        cropData.forEach(f -> System.out.println(f.getNameOfTheBeneficiary()));
    }

    private static void getVillageAndName(List<CropInsuranceDTO> cropList) {
        List<VillageAndName> villName = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> f.getVillageName() != null && f.getNameOfTheBeneficiary() != null)
                .map(m -> {
                    VillageAndName vill = new VillageAndName();
                    vill.setName(m.getNameOfTheBeneficiary());
                    vill.setVillage(m.getVillageName());
                    return vill;
                })
                .toList();
        villName.forEach(f -> {
            System.out.println("Vill Name===" + f.getVillage() + "===Beneficiary Name===" + f.getName());
        });
    }

    private static void getDistinctMandals(List<CropInsuranceDTO> cropList) {
        List<String> mandals = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .map(CropInsuranceDTO::getMandalName)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        mandals.forEach(System.out::println);
    }

    private static void findMinClaimRow(List<CropInsuranceDTO> cropList) {
        CropInsuranceDTO minClaim = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .max(comparing(CropInsuranceDTO::getClaimAmountRs))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Maximum CropInsuranceDTO :==" + minClaim);
    }

    private static void findMaxClaimRow(List<CropInsuranceDTO> cropList) {
        CropInsuranceDTO maxClaim = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .max(comparing(CropInsuranceDTO::getClaimAmountRs))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Maximum CropInsuranceDTO :==" + maxClaim);
    }

    private static void claimSummaryStats(List<CropInsuranceDTO> cropList) {
        DoubleSummaryStatistics doubleSummaryStatistics = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .filter(f -> {
                    if (f.getClaimAmountRs() > 0) {
                        return true;
                    }
                    return false;
                })
                .collect(summarizingDouble(CropInsuranceDTO::getClaimAmountRs));
        System.out.println("DoubleSummaryStatistics:== " + doubleSummaryStatistics);
    }

    private static void totalClaimAmount(List<CropInsuranceDTO> cropList) {
        double total = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .collect(summingDouble(CropInsuranceDTO::getClaimAmountRs));
        System.out.println("Total Claim Amount:==" + total);
    }

    private static void averageClaimAmount(List<CropInsuranceDTO> cropList) {
        double average = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .collect(averagingDouble(CropInsuranceDTO::getClaimAmountRs));
        System.out.println("Average Amount===" + average);
    }

    private static void countTheCropsByMandal(List<CropInsuranceDTO> cropList) {
        long count = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .map(CropInsuranceDTO::getMandalName)
                .filter(Objects::nonNull)
                .filter(f -> f.equalsIgnoreCase("bukkapatnam"))
                .count();
        System.out.println("No of Claims Bukkapatnam:==" + count);
    }

    private static void findTheMinClaim(List<CropInsuranceDTO> cropList) {
        double minAmount = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .mapToDouble(CropInsuranceDTO::getClaimAmountRs)
                .filter(d -> d > 0)
                .min().orElseThrow(NoSuchElementException::new);
        System.out.println("Min Claim Amount:==" + minAmount);
    }

    private static void findTheMaxClaim(List<CropInsuranceDTO> cropList) {
        double maxAmount = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .parallelStream()
                .filter(Objects::nonNull)
                .mapToDouble(CropInsuranceDTO::getClaimAmountRs)
                .max().orElseThrow(NoSuchElementException::new);
        System.out.println("Max Claim Amount:==" + maxAmount);
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

        //listCrop.forEach(c -> System.out.println(c.getMandalName()));
        return listCrop;
    }
}

class VillageAndName {
    private String village;
    private String name;
    private double claim;

    public double getClaim() {
        return claim;
    }

    public void setClaim(double claim) {
        this.claim = claim;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}