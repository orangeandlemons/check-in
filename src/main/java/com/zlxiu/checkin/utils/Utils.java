package com.zlxiu.checkin.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * 调用人脸识别脚本并获取识别结果
     *
     * @param path1 人脸图片地址
     * @param path2 考勤图片地址
     * @return 2：成功 1：失败 4图片不清楚或未检测到人脸
     */
    public static Integer face(String path1, String path2) {
        StringBuffer sb = new StringBuffer();
        try {
            //写入Python编译器的位置和脚本位置，以及图片地址
            String[] args = new String[]{"E:\\Anaconda3\\python.exe", "D:\\project\\checkin\\face.py", path1, path2};
            //调用服务器命令脚本来执行功能需要
            Process process = Runtime.getRuntime().exec(args);
            process.waitFor();
            //获取脚本输出值
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            //如果返回值不为空，则添加进字符串
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(String.valueOf(sb));
    }


    /**
     * 上传图像至服务器
     *
     * @param file 上传的图片文件
     * @param type 1:老师头像，2:学生头像，3:课程头像，4:人脸信息，5:考勤时的人脸信息
     * @param id   编号
     * @return 存储的映射路径
     */
    public static String saveImage(MultipartFile file, String type, String id) {
        if (file.isEmpty()) {
            return null;
        }
        String prefix = "";
        String parentPath = "";
        switch (type) {
            case "1":
                prefix = "t";
                parentPath = "avatars";
                break;
            case "2":
                prefix = "s";
                parentPath = "avatars";
                break;
            case "3":
                prefix = "c";
                parentPath = "avatars";
                break;
            case "4":
                prefix = "";
                parentPath = "face";
                break;
            case "5":
                prefix = "";
                parentPath = "check";
                break;
            default:
                break;
        }

        String path = "src/main/resources/static/" + parentPath + "/";
        File folder = new File(path);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String ext = ".png";
        String photoName = prefix + id + ext;
        try {
            File newFile = new File(folder.getAbsolutePath() + File.separator + photoName);
            file.transferTo(newFile);
            return "/image/" + parentPath + "/" + photoName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String renameImage(String path, String name) {
        String realPath = "src/main/resources/static/avatars/";
        String virtualPath = path.substring(0, path.lastIndexOf("/") + 1);
        String oldName = path.substring(path.lastIndexOf("/") + 1);
        String ext = oldName.substring(oldName.lastIndexOf("."));
        File file = new File(realPath + oldName);
        File file1 = new File(realPath + name + ext);
        boolean b = file.renameTo(file1);
        return b ? virtualPath + name + ext : null;
    }

    /**
     * 删除图片
     *
     * @param path 图片的路径
     */
    public static void deleteImage(String path, String parentPath) {
        String p = "src/main/resources/static/" + parentPath + path.substring(path.lastIndexOf("/"));
        File file = new File(p);
        if (!path.isEmpty()) {
            file.delete();
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o 需要判断的对象
     * @return 为空返回true, 不为空返回false
     */
    public static boolean isEmpty(Object o) {
        if (o == null || Objects.isNull(o)) {
            return true;
        }
        if (o instanceof String) {
            if ("".equals(o.toString().trim())) {
                return true;
            }
            return "undefined".equals(o.toString().trim());
        } else if (o instanceof List) {
            return ((List) o).size() == 0;
        } else if (o instanceof Map) {
            return ((Map) o).size() == 0;
        } else if (o instanceof Set) {
            return ((Set) o).size() == 0;
        } else if (o instanceof Object[]) {
            return ((Object[]) o).length == 0;
        } else if (o instanceof int[]) {
            return ((int[]) o).length == 0;
        } else if (o instanceof long[]) {
            return ((long[]) o).length == 0;
        }
        return false;
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isPhone(String mobiles) {
        boolean flag = false;
        try {
            String pattern = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
