package com.example.myawesomequiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myawesomequiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 10;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("C Programming");
        insertCategory(c1);

        Category c2 = new Category("Geography");
        insertCategory(c2);

        Category c3 = new Category("Math");
        insertCategory(c3);

        Category c4 = new Category("Python Programming");
        insertCategory(c4);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {


        Question q1 = new Question("1. Who is father of C Language?",

                "A. Bjarne Stroustrup",
                "B. Dennis Ritchie",
                "C. James A. Gosling", 1,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q1);


        Question q2 = new Question("2. For 16-bit compiler allowable range for integer constants is ______ ?",

                "A. -3.4e38 to 3.4e38",
                "B. -32767 to 32768",
                "C. -32768 to 32767", 3,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q2);


        Question q3 = new Question("3. C programs are converted into machine language with the help of",

                "A. An Editor",
                "B. A compiler",
                "C. An operating system", 2,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q3);


        Question q4 = new Question("5. A C variable cannot start with",

                "A. both (b) and (c)",
                "B. A number",
                "C. A special symbol other than underscore", 1,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q4);


        Question q5 = new Question("5. Which of the following is allowed in a C Arithmetic instruction",

                "A. []",
                "B. {}",
                "C. ()", 3,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q5);


        Question q6 = new Question("6. What is an array?",

                "A. An array is a collection of variables that are of the dissimilar data type.",
                "B. An array is a collection of variables that are of the same data type.",
                "C. An array is not a collection of variables that are of the same data type.", 2,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q6);


        Question q7 = new Question("7. Prototype of a function means _____?",

                "A. Name of Function",
                "B. Output of Function",
                "C. Declaration of Function", 3,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q7);


        Question q8 = new Question("8. Name the loop that executes at least once.",

                "A. For",
                "B. If",
                "C. do-while", 3,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q8);


        Question q9 = new Question("9. Which one is not a reserve keyword in C Language?",

                "A. auto",
                "B. main",
                "C. case", 2,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q9);


        Question q10 = new Question("10. C Language was developed in the year ____",

                "A. 1970",
                "B. 1975",
                "C. 1980", 1,

                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q10);


        Question q11 = new Question("1. What is right way to Initialization array?",

                "A. int num[6] = { 2, 4, 12, 5, 45, 5 } ; ",
                "B. int n{} = { 2, 4, 12, 5, 45, 5 } ; ",
                "C. C. int n{6} = { 2, 4, 12 } ; ", 1,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q11);


        Question q12 = new Question("2. An array elements are always stored in _________ memory locations.",

                "A. Sequential ",
                "B. Random ",
                "C. Sequential and Random ", 1,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q12);


        Question q13 = new Question("3. Bitwise operators can operate upon?",

                "A. double and chars ",
                "B. floats and doubles ",
                "C. ints and chars ", 3,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q13);


        Question q14 = new Question("5. What is C Tokens?",

                "A. The smallest individual units of c program",
                "B. A & C Both",
                "C. The basic element recognized by the compiler", 2,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q14);


        Question q15 = new Question("5. Which is the right way to declare constant in C?",

                "A. int const var = 10; ",
                "B. const int var = 10; ",
                "C. A & B Both ", 3,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q15);


        Question q16 = new Question("6. Which one of the following is not a linear data structure?",

                "A. Array",
                "B. Binary Tree",
                "C. Queue", 2,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q16);


        Question q17 = new Question("7. Queue is a _____________ list.",
                "A. FILO",
                "B. LIFO",
                "C. FIFO", 3,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q17);


        Question q18 = new Question("8. The C language is",
                "A. Context free language",
                "B. Context sensitive language",
                "C. Regular language", 1,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q18);


        Question q19 = new Question("9. An uninitialized pointer in C is called ___",
                "A. Constructor ",
                "B. dangling pointer ",
                "C. Wild Pointer ", 3,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q19);


        Question q20 = new Question("10. A pointer that is pointing to NOTHING is called ____",
                "A. VOID Pointer",
                "B. NULL Pointer",
                "C. DANGLING Pointer", 2,

                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q20);


        Question q21 = new Question("1. To represent hierarchical relationship between elements, which data structure is suitable?",

                "A. Priority ; ",
                "B. Tree ",
                "C. Dqueue ; ", 2,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q21);


        Question q22 = new Question("2. The Default Parameter Passing Mechanism is called as",

                "A. Call by Value ",
                "B. Call by Reference ",
                "C. Call by Address ", 1,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q22);


        Question q23 = new Question("3. What will happen if in a C program you assign a value to an array element whose subscript exceeds the size of array?",

                "A. The element will be set to 0. ",
                "B. The compiler would report an error. ",
                "C. The program may crash if some important data gets overwritten. ", 3,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q23);


        Question q24 = new Question("5. What is Keywords?",

                "A. Keywords have some predefine meanings and these meanings can be changed. ",
                "B. Keywords have some unknown meanings and these meanings cannot be changed. ",
                "C. Keywords have some predefine meanings and these meanings cannot be changed. ", 3,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q24);


        Question q25 = new Question("5. What is constant??",

                "A. Constants have fixed values that do not change during the execution of a program ",
                "B. Constants have fixed values that change during the execution of a program ",
                "C. Constants have unknown values that may be change during the execution of a program ", 1,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q25);


        Question q26 = new Question("6. In switch statement, each case instance value must be _______?",

                "A. Constant",
                "B. Variable",
                "C. Special Symbol", 1,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q26);


        Question q27 = new Question("7. A pointer pointing to a memory location of the variable even after deletion of the variavle is known as _____",

                "A. far pointer",
                "B. dangling pointer",
                "C. null pointer", 2,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q27);


        Question q28 = new Question("8. What is the work of break keyword?",

                "A. Halt execution of program",
                "B. Restart execution of program",
                "C. Exit from loop or switch statement", 3,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q28);


        Question q29 = new Question("9. What is Dequeue? ",

                "A. Elements can be added from front ",
                "B. Elements can be added to or removed from either the front or rear ",
                "C. Elements can be added from rear ", 2,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q29);


        Question q30 = new Question("10. In which tree, for every node the height of its left subtree and right subtree differ almost by one? ",

                "A. Binary search tree ",
                "B. AVL tree ",
                "C. Threaded Binary Tree", 2,

                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q30);


        Question q31 = new Question("Geography, Medium: B is correct",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q31);

        Question q32 = new Question("Math, Hard: C is correct",
                "A", "B", "C", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q32);

        Question q33 = new Question("Python, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, Category.PYTHON);
        insertQuestion(q33);

        Question q34 = new Question("Non existing, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, 4);
        insertQuestion(q34);

        Question q35 = new Question("Non existing, Medium: B is correct",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, 5);
        insertQuestion(q35);


        Question q36 = new Question("Math : B is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q36);


    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}