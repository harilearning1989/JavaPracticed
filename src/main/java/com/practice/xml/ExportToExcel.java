package com.practice.xml;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

@SuppressWarnings({ "unused", "rawtypes" })
public class ExportToExcel {

	int rownum = 0;
	HSSFSheet firstSheet;
	Collection<File> files;
	HSSFWorkbook workbook;
	File exactFile;

	{
		workbook = new HSSFWorkbook();
		firstSheet = workbook.createSheet("FIRST SHEET");
		HSSFRow headerRow = firstSheet.createRow(rownum);
		headerRow.setHeightInPoints(40);
		Cell headerCell;

	}

	public static void main(String args[]) {
		ExportToExcel class1 = new ExportToExcel();
		class1.readfile();
	}

	void readfile() {
		try {
			FileInputStream fstream = new FileInputStream("D:/Sample CSV file for importing contacts.csv");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			int i = 1;
			int seqno = 1;
			while ((strLine = br.readLine()) != null) {
				StringTokenizer st1 = new StringTokenizer(strLine, ",");

				List<String> l1 = new ArrayList<String>();
				while (st1.hasMoreTokens()) {
					l1.add(st1.nextToken());
				}

				try {
					boolean retu = writenameinsheet(l1);
				} catch (Exception e) {
					e.printStackTrace();
				}

				seqno++;
				i = 1;
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(new File("D:/ExcelSheet.xls"));
				workbook.write(fos);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.flush();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean writenameinsheet(List<String> l1) throws Exception {
		try {
			HSSFRow row = firstSheet.createRow(rownum);

			for (int j = 0; j < l1.size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(l1.get(j));
			}
			rownum++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return true;
	}
}