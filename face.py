import face_recognition,sys


def getEncoding(image):
    # 定位图片中的人脸位置，共查找一次，使用hog模型
    face_locations = face_recognition.face_locations(image, number_of_times_to_upsample=1, model='hog')
    # 获取特征向量
    image_encoding = face_recognition.face_encodings(image, face_locations)
    return image_encoding


def recongnition(image, face):
    # 加载签到图片
    image=face_recognition.load_image_file(image)
    # 获取签到图片的面部编码
    image_encoding=getEncoding(image)
    # 如果未获取到面部编码，便返回4，重新循环
    if len(image_encoding) < 1:
        return 4
    else:
        img2 = image_encoding[0]
    # 加载人脸图片
    face_image = face_recognition.load_image_file(face)
    # 获取人脸图片的面部编码
    face_encoding=getEncoding(face_image)
    # 如果未获取到面部编码，便返回4，重新循环
    if len(face_encoding) < 1:
        return 4
    else:
        img1 = face_encoding[0]
    #两张图片的面部编码进行匹配，tolerance为阈值，越小则人脸匹配越严格。这里设置0.49
    matches = face_recognition.compare_faces([img1], img2, tolerance=0.49)
    # 若成功则返回2，失败返回1
    if True in matches:
        return 2
    return 1


if __name__ == '__main__':
    # 从程序外部获取图片地址
    path1 = sys.argv[1]
    path2 = sys.argv[2]
    result = recongnition(path1, path2)
    if result==4:
        for i in range(3):
            result = recongnition(path1, path2)

    print(result)



