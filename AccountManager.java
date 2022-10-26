import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.io.BufferedReader;

public class AccountManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AllUserAccounts userAccounts = new AllUserAccounts();
        Boolean Open = true;
        do
        {
            System.out.println("Welcome!Please type below:");
            System.out.println("1.login\n2.new\n3.exit");
            System.out.println(">>>>>>");
            String input = scanner.next();
            switch(input){
                case "new":
                Boolean creatingAccount = true;
                String newUsername;
                String newPassword;
                do{
                    System.out.println("Create");
                    System.out.println("User name");
                    newUsername = scanner.next();
                    System.out.println("Password");
                    newPassword = scanner.next();


                    if(!userAccounts.accountDictionary.containsKey(newUsername)){


                        System.out.println("Your First name");
                        String newFirstName = scanner.next();

                        System.out.println("Your Last name");
                        String newLastName = scanner.next();

                        System.out.println("Your birth day");
                        String newBirthday = scanner.next();

                        userAccounts.addAccountToFile(newUsername,newPassword,newFirstName,newLastName,newBirthday);
                        System.out.println("Saved!");
                        break;
                    }else{
                        System.out.println("Already Exists");
                        Boolean exitQuestions = true;
                        while(exitQuestions){
                            System.out.println("Try again");
                            System.out.println("y/n");
                            String exitYesOrNo = scanner.next();
                            if(exitYesOrNo.equals("y")){
                                exitQuestions = false;
                            } else if(exitYesOrNo.equals("n")){
                                creatingAccount = false;
                                exitQuestions = false;
                                break;
                            }
                            else{
                                System.out.println("type y or n");
                            }
                        }
                    }
                }while(creatingAccount);
                break;
            case "login":
                Boolean attempting = true;
                while(attempting){
                    System.out.println("\n login");
                    System.out.println("Username");
                    String userNameEntry = scanner.next();
                    System.out.println("Password");
                    String passwordEntry = scanner.next();
                    if (userAccounts.accountDictionary.containsKey(userNameEntry)){
                        if(userAccounts.accountDictionary.get(userNameEntry)[0].equals(passwordEntry)){
                            Boolean loggedin = true;
                            do
                            {
                                System.out.println("welcome:"+userAccounts.accountDictionary.get(userNameEntry)[1]);
                                System.out.println("1. info");
                                System.out.println("2. exit");
                                String loggedInEntry = scanner.next();
                                switch(loggedInEntry){
                                    case "info":
                                    String[] userInfo = userAccounts.accountDictionary.get(userNameEntry);
                                    System.out.println("username:"+userNameEntry);
                                    System.out.println("First name:"+userInfo[1]);
                                    System.out.println("Last name:"+userInfo[2]);
                                    System.out.println("Birthday:"+userInfo[3]);
                                    break;
                                    case "exit":
                                        loggedin = false;
                                        attempting = false;
                                        break;
                                }
                            }while(loggedin);
                        }else{
                            Boolean askingExit = true;
                            while(askingExit){
                                System.out.println("Password not correct, try again y or n");
                                String exitResponse = scanner.next();
                                if(exitResponse.equals("n")){
                                    attempting = false;
                                    askingExit = false;
                                }else if (exitResponse.equals("y")){
                                    askingExit = false;
                                }else{
                                    System.out.println("type y or n");
                                }
                            }
                    }
                        
                        
                    }else{
                        Boolean askingExit = true;
                        while(askingExit){
                            System.out.println("not exist. would you like to try again y or n");
                            String exitResponse = scanner.next();
                            if(exitResponse.equals("n")){
                                attempting = false;
                                askingExit = false;
                            }else if (exitResponse.equals("y")){
                                askingExit = false;
                            }else{
                                System.out.println("type y or n");
                            }
                        }
                    }
                    }
                    break;
                
                case "exit":
                    System.out.println("Exit");
                    Open = false;
                    break;
        }
        }
        while(Open);
            System.out.println("Bye");
            scanner.close();
        }
    }

    class AllUserAccounts{
        Hashtable<String,String[]> accountDictionary;
        AllUserAccounts(){
            File accountFile = new File(System.getProperty("user.dir")+"/accounts.txt");
            BufferedReader accountFilReader;
            accountDictionary = new Hashtable<String,String[]>();
            try{
                if (accountFile.createNewFile()){
                    System.out.println("File saved!");
                }
                accountFilReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/accounts.txt"));
                String new_line = accountFilReader.readLine();
                while(new_line!=null){
                    String[] split_line = new_line.split(":");
                    accountDictionary.put(split_line[0],split_line[1].split(","));
                    new_line = accountFilReader.readLine();
                }
            }catch(IOException error){
                System.out.println("Error");
            }
        }
        void addAccountToFile(String username, String password,String firstName, String lastName,String birthday){
            String accoutString = username + ":" + password + "," + firstName + "," + lastName + ',' + birthday + ",";
            String[] accountInfo  = {password,firstName,lastName,birthday};
            try{
                FileWriter newAccountWriter = new FileWriter(System.getProperty("user.dir")+"/accounts.txt");
                accountDictionary.put(username,accountInfo);
                newAccountWriter.write(accoutString+"\n");
                newAccountWriter.close();

            }catch(IOException error){
                System.out.println("Error");
            }
        }

        
        // void getAccountFromFile(String username, String password,String firstName, String lastName,String birthday){
        //     String accoutString = username + ":" + password + "," + firstName + "," + lastName + ',' + birthday + ",";
        //     String[] accountInfo  = {password,firstName,lastName,birthday};
        //     try{
        //         FileWriter newAccountWriter = new FileWriter(System.getProperty("user.dir")+username+"/accounts.txt");
        //         accountDictionary.put(username,accountInfo);
        //         newAccountWriter.write(accoutString+"\n");
        //         newAccountWriter.close();

        //     }catch(IOException error){
        //         System.out.println("Error");
        //     }
        // }



    }
