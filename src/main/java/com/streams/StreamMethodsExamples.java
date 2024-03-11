package com.streams;

import com.github.DownloadGitHubFiles;
import com.github.dto.CropInsuranceDTO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMethodsExamples {
    //https://stackify.com/streams-guide-java-8/
    public static void main(String[] args) {
        try {
            DownloadGitHubFiles.downloadFile("csv/crop_insurance.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String cropFileLocation = "/Users/hariduddukunta/MyWork/DataFiles/csv/crop_insurance.csv";
        List<CropInsuranceDTO> cropList = getCropDetails(cropFileLocation);
        System.out.println("Size of the crop===" + cropList.size());

        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("OneAndOnly");
        list.add("Derek");
        list.add("Change");
        list.add("factory");
        list.add("justBefore");
        list.add("Italy");
        list.add("Italy");
        list.add("Thursday");
        list.add("");
        list.add("");
        streamMethodsExamples(cropList, list);
    }

    private static void streamMethodsExamples(
            List<CropInsuranceDTO> cropList,
            ArrayList<String> list) {
        cropList.add(null);
        //getDistinctMandals(cropList);
        //getAllFormersNames(cropList);
        //getTheMandalWiseFormerCount(cropList);
        //getTheMaxInsurance(cropList);
        //getDistinctBranches(cropList);
        //getVillAndFormerName(cropList);

        //allMatch,anyMatch,builder,collect,concat,count,distinct,empty,filter,findAny,findFirst,flatMap,
        //flatMapToDouble,flatMapToInt,flatMapToLong,forEach,forEachOrdered,generate,iterate,limit,map,mapToDouble,
        //mapToInt,mapToLong,max,min,noneMatch,of,peek,reduce,skip,sorted,toArray

        allMatchExample(cropList);

        boolean isValid = list.stream().anyMatch(element -> element.contains("h")); // true
        boolean isValidOne = list.stream().allMatch(element -> element.contains("h")); // false
        boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h"));
    }

    private static void allMatchExample(List<CropInsuranceDTO> cropList) {
        Stream<String> stream = Stream.of("one", "two", "three", "fou1r");
        Predicate<String> containsDigit = s -> s.contains("\\d+") == false;
        boolean match = stream.allMatch(containsDigit);
        System.out.println(match);
    }

    private static void getVillAndFormerName(List<CropInsuranceDTO> cropList) {
        List<FormerVillageName> formerList = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(f -> {
                    if (f.getClaimAmountRs() > 50000) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .map(StreamMethodsExamples::getVillFormer)
                .toList();
        System.out.println(formerList.size());
    }

    private static FormerVillageName getVillFormer(CropInsuranceDTO dto) {
        FormerVillageName former = new FormerVillageName();
        former.setFormerName(dto.getNameOfTheBeneficiary());
        former.setVillName(dto.getVillageName());
        return former;
    }

    private static void getDistinctBranches(List<CropInsuranceDTO> cropList) {
        List<String> distinctBranches = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(CropInsuranceDTO::getBranchName)
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        System.out.println(distinctBranches.size());
        distinctBranches.forEach(System.out::println);
    }

    private static void getTheMaxInsurance(List<CropInsuranceDTO> cropList) {
        DoubleSummaryStatistics amountStats =
                Optional.ofNullable(cropList)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.summarizingDouble(CropInsuranceDTO::getClaimAmountRs));
        System.out.println(amountStats.getMax() + "===Min==" + amountStats.getMin() +
                "===AVG===" + amountStats.getAverage() + "==Total==" + amountStats.getSum());
    }

    private static void getTheMandalWiseFormerCount(List<CropInsuranceDTO> cropList) {
        Map<String, List<CropInsuranceDTO>> mandalName =
                Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(CropInsuranceDTO::getMandalName));
        //System.out.println("Person grouped by cities in Java 8: " + mandalName);
        List<CropInsuranceDTO> list = mandalName.get("Bukkapatnam");
        System.out.println(list.size());
    }

    private static void getAllFormersNames(List<CropInsuranceDTO> cropList) {
        List<String> formerNames = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(CropInsuranceDTO::getNameOfTheBeneficiary)
                .collect(Collectors.toList());
        formerNames.forEach(System.out::println);
        long total = formerNames.stream().count();
        System.out.println("Total Formers===" + total);
    }

    private static void getDistinctMandals(List<CropInsuranceDTO> cropList) {
        List<String> mandals = Optional.ofNullable(cropList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(CropInsuranceDTO::getMandalName)
                .distinct()
                .collect(Collectors.toList());

        mandals.forEach(System.out::println);
        long total = mandals.stream().count();
        System.out.println("Total Madnals===" + total);
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

class FormerVillageName {
    private String formerName;
    private String villName;

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
    }

    public String getVillName() {
        return villName;
    }

    public void setVillName(String villName) {
        this.villName = villName;
    }
}