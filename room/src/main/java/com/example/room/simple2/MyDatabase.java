package com.example.room.simple2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created  by Administrator on 2021/6/19 21:48
 * 必须为抽象类
 * 1、使用Migration进行数据库版本更新
 * 问题：如果用户数据库的版本为1，而当前版本要安装的app的数据库为3，怎么办？
 * 解决：Room会判断有没有直接从1到3的解决方方案，如果有就直接执行1-3的升级方案 ，如果没有Room会按照顺序先后执行Migration(1,2)、Migration(2,3)来完成数据库的升级
 *
 * 2、假设我们把数据库版本升级为4 却没有写相应的Migration，则会报IllegalStateException异常，加入fallbackToDestructiveMigration(),
 * 该方法在升级异常时 重建数据表 同时数据也会丢失
 *
 *3、 Room在升级的过程中 都会导出一个Schema文件，是一个json格式的文件 包含数据库的基本信息 有了该文件我们能清楚的知道历次数据库的更新情况 方便我们排查问题
 * 需要把exportSchema = true
 *
 * 4、销毁和重建策略
 * 在SQLite中修改表结构比较麻烦，例如将student中sex的类型从INTEGET改成TEXT，最好的方式是销毁和重建策略
 *  1.创建一张符合表结构的临时表temp_student
 *  2.将数据从旧表student复制到临时表
 *  3、删除旧表student
 *  4.将临时表重命名为student
 *
 * 5、预填充数据库
 * 据供一些默认数给我们去使用，我们可以将数据库文件放入assets目录一起打包发布，在用户首次打开App时使用createFromAsset和createFromFile()创建Room数据库
 */
@Database(entities = {Student.class},version = 4,exportSchema = true)
public abstract class MyDatabase extends RoomDatabase {
    private  static  final  String DATABASE_NAME="stu_db.db";
    private  static  MyDatabase mInstance;
    //不能私有化构造方法 因为我们的room要根据构造方法创建我们的MyDatabase实现类
   // private MyDatabase(){}


    public static synchronized MyDatabase getmInstance(Context context) {
        if(mInstance==null){
            mInstance= Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_NAME)
                   // .allowMainThreadQueries()//允许在主线程中查询
                    .addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4)
                    .fallbackToDestructiveMigration()
                    //.createFromAsset("prestudent.db")//数据没显示？？？
                    .build();
        }
        return mInstance;
    }


   static final Migration MIGRATION_1_2= new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //记得在实体类中添加相应的字段
            database.execSQL("ALTER TABLE student ADD COLUMN sex INTEGER NOT NULL DEFAULT 1 ");
        }
    };


    static final Migration MIGRATION_2_3= new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //记得在实体类中添加相应的字段
            database.execSQL("ALTER TABLE student ADD COLUMN score INTEGER NOT NULL DEFAULT 80 ");
        }
    };

    static final Migration MIGRATION_3_4= new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //销毁和重建策略
            //记得在实体类中添加相应的字段  只有INTEGER才使用NOT NULL
            database.execSQL("CREATE TABLE temp_student (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "name TEXT, " +
                    "age INTEGER NOT NULL," +
                    "sex TEXT DEFAULT 'M',"+
                    "score INTEGER NOT NULL DEFAULT 80 "+
                    ")");
            database.execSQL("INSERT INTO temp_student (name,age,sex,score)" +
                    "SELECT name,age,sex,score FROM student");
            database.execSQL("DROP TABLE student");
            database.execSQL("ALTER TABLE temp_student RENAME TO student");
        }
    };


    //提供一个获取StudentDao的抽象方法 room自动帮我们实现
    public abstract StudentDao getStudentDao();
}
