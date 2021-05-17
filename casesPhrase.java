public class casesPhrase extends messagePhrase {
    
    private String file;
    public casesPhrase(){
        this.file = "tempcase.txt";
    }

    public casesPhrase(String fileName){
        this.file = fileName;
    }

    // public void getCurrentFile(){
    //     super(this.file);
    // }

}