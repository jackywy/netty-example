package com.wy.gps.distance.data;

import com.wy.gps.distance.domain.PositionDMS;
import com.wy.gps.distance.helper.PositionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟正式数据
 */
public class PositionsDataUtil {

    public static List<PositionDMS> getPositionDMSData() {
        List<PositionDMS> positionDMSList = new ArrayList<PositionDMS>();
        String positionDMSStr0 = "";
        String positionDMSStr1 = "";
        String positionDMSStr2 = "";
        String positionDMSStr3 = "";
        String[] positionDMSArray = {positionDMSStr0, positionDMSStr1, positionDMSStr2, positionDMSStr3};
        for (int j = 0; j < positionDMSArray.length; j++) {
            String[] strArray1 = positionDMSArray[j].split("@");
            for (int i = 0; i < strArray1.length; i++) {
                String[] strArray2 = strArray1[i].split(",");
                PositionDMS positionDMS = new PositionDMS(PositionUtils.covertDDDToDMS(Double.valueOf(strArray2[1])), PositionUtils.covertDDDToDMS(Double.valueOf(strArray2[2])), Long.valueOf(strArray2[0]));
                positionDMSList.add(positionDMS);
            }
        }


        return positionDMSList;
    }

    public static List<PositionDMS> getPositionDMSDataFromFile() {
        List<PositionDMS> positionDMSList = new ArrayList<PositionDMS>();
        String filePath = "C:\\Users\\wangyang\\IdeaProjects\\netty-example\\src\\main\\java\\com\\wy\\gps\\distance\\helper\\data.txt";
        readTxtFile(filePath, positionDMSList);
        return positionDMSList;
    }


    public static void readTxtFile(String filePath, List<PositionDMS> positionDMSList) {
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] strArray1 = lineTxt.split("@");
                    for (int i = 0; i < strArray1.length; i++) {
                        String[] strArray2 = strArray1[i].split(",");
                        PositionDMS positionDMS = new PositionDMS(PositionUtils.covertDDDToDMS(Double.valueOf(strArray2[0])), PositionUtils.covertDDDToDMS(Double.valueOf(strArray2[1])));
                        positionDMSList.add(positionDMS);
                    }
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        List<PositionDMS> positionDMSDataFromFile = getPositionDMSDataFromFile();
        System.out.println(positionDMSDataFromFile.size());
    }
}
