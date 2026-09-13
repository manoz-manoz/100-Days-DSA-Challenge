public class LinearTimeExample {
    public static void main(String ar[])
    {
        int arr[]={10,20,30,40,50};
        int key=60;
        //traversing
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i]+" ");
        //linear search
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==key)
                System.out.println(key+"Found");
        }
        System.out.println(key+"Not Found");

        
    }
    
}
