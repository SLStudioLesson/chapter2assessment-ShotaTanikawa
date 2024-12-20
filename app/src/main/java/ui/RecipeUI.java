package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        ArrayList<String> recipes = new ArrayList<>();
        recipes = fileHandler.readRecipes();

        //空の確認
        if(recipes.isEmpty()){
            System.out.println("No recipes available");
            return;
        }

        //出力
        System.out.println("Recipes");
        for (String recipe : recipes){
            //,で区切る
            String[] parts = recipe.split(",");

            //レシピ名の出力
            //料理名を出力
            System.out.println("-----------------------------------");
            System.out.println("Recipe Name: " + parts[0]);
            System.out.print("Main Ingredients:");
            //材料の出力
            for (int i = 1; i < parts.length; i++){
                System.out.print(parts[i]);
            //コンマを最後以外に入れていく
                if (i < parts.length - 1){
                    System.out.print(", ");
                }
            }
            System.out.println();

        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //レシピ名の入力
        System.out.print("Enter recipe name: ");
        String recipeName = reader.readLine();

        //材料の入力
        System.out.print("Enter main ingredients (comma separated): ");
        String ingredients = reader.readLine();

        //RecipeFileHandlerを使って新しいレシピを追加する
        RecipeFileHandler handler = new RecipeFileHandler();
        handler.addRecipe(recipeName, ingredients);

        //成功メッセージ
        System.out.println("Recipe added successfully.");
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}

