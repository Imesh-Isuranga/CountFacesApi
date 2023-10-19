import sys
import sys
import face_recognition
import cv2

def python_method(input_string):
    #loading the image to detect
    image_to_detect = cv2.imread(input_string)




    #find all faces locations using face_locations() function
    #model can be `cnn` or `hog`
    #number_of_times_to_upsample=1 higher and detect more faces

    all_faces_locations = face_recognition.face_locations(image_to_detect,model="hog")

    return len(all_faces_locations)

if __name__ == "__main__":
    input_string = sys.stdin.readline().strip()
    print("imesh isuranga")
