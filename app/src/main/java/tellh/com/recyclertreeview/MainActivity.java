package tellh.com.recyclertreeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

import tellh.com.recyclertreeview.bean.Folder;
import tellh.com.recyclertreeview.view.SelectTreeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SelectTreeView selectTreeView = findViewById(R.id.select_tree_view);

        String str = "{\n" +
                "\t\"children\": [{\n" +
                "\t\t\"children\": [],\n" +
                "\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\"name\": \"测试子部门\",\n" +
                "\t\t\"itemList\": [{\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"2edce931c95c4dfa85f74e63ebaf62c5\",\n" +
                "\t\t\t\"name\": \"泉州-小电65656\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"3e54fc5bd8a24a1f8589c162bc1260e6\",\n" +
                "\t\t\t\"name\": \"2422343\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"57b07f3fc3454ec7b8aaa68d5be33712\",\n" +
                "\t\t\t\"name\": \"泉州-小电3425456\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"5c56f6cfb62b4d9195b2af51f9685d7f\",\n" +
                "\t\t\t\"name\": \"泉州-东海A10066\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"799f3465754247d9b962de57ebc67758\",\n" +
                "\t\t\t\"name\": \"闽C747474\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"963aef4009754321b4f61653dbfc8e0d\",\n" +
                "\t\t\t\"name\": \"86588335\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"afa63486c2bc42ec80963ec84923b4b0\",\n" +
                "\t\t\t\"name\": \"泉州-小电66666\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"f0147382fde84277b21bbba6182ad06b\",\n" +
                "\t\t\t\"name\": \"86588336\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"f414a5dbd4a64107a74cca24a3582c28\",\n" +
                "\t\t\t\"name\": \"闽C747475\"\n" +
                "\t\t}],\n" +
                "\t\t\"parentId\": \"c2e7783bc2754801886b97b460274c24\"\n" +
                "\t},{\n" +
                "\t\t\"children\": [],\n" +
                "\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\"name\": \"测试子部门111\",\n" +
                "\t\t\"itemList\": [{\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"2edce931c95c4dfa85f74e63ebaf62c5\",\n" +
                "\t\t\t\"name\": \"泉州-小电65656\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"3e54fc5bd8a24a1f8589c162bc1260e6\",\n" +
                "\t\t\t\"name\": \"2422343\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"57b07f3fc3454ec7b8aaa68d5be33712\",\n" +
                "\t\t\t\"name\": \"泉州-小电3425456\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"5c56f6cfb62b4d9195b2af51f9685d7f\",\n" +
                "\t\t\t\"name\": \"泉州-东海A10066\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"799f3465754247d9b962de57ebc67758\",\n" +
                "\t\t\t\"name\": \"闽C747474\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"963aef4009754321b4f61653dbfc8e0d\",\n" +
                "\t\t\t\"name\": \"86588335\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"afa63486c2bc42ec80963ec84923b4b0\",\n" +
                "\t\t\t\"name\": \"泉州-小电66666\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"f0147382fde84277b21bbba6182ad06b\",\n" +
                "\t\t\t\"name\": \"86588336\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"f414a5dbd4a64107a74cca24a3582c28\",\n" +
                "\t\t\t\"name\": \"闽C747475\"\n" +
                "\t\t}],\n" +
                "\t\t\"parentId\": \"c2e7783bc2754801886b97b460274c24\"\n" +
                "\t}],\n" +
                "\t\"depId\": \"c2e7783bc2754801886b97b460274c24\",\n" +
                "\t\"name\": \"c测试部门\",\n" +
                "\t\"itemList\": [{\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"2edce931c95c4dfa85f74e63ebaf62c5\",\n" +
                "\t\t\t\"name\": \"泉州-小电65656\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"3e54fc5bd8a24a1f8589c162bc1260e6\",\n" +
                "\t\t\t\"name\": \"2422343\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"57b07f3fc3454ec7b8aaa68d5be33712\",\n" +
                "\t\t\t\"name\": \"泉州-小电3425456\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"5c56f6cfb62b4d9195b2af51f9685d7f\",\n" +
                "\t\t\t\"name\": \"泉州-东海A10066\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"799f3465754247d9b962de57ebc67758\",\n" +
                "\t\t\t\"name\": \"闽C747474\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"963aef4009754321b4f61653dbfc8e0d\",\n" +
                "\t\t\t\"name\": \"86588335\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"afa63486c2bc42ec80963ec84923b4b0\",\n" +
                "\t\t\t\"name\": \"泉州-小电66666\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"f0147382fde84277b21bbba6182ad06b\",\n" +
                "\t\t\t\"name\": \"86588336\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"depId\": \"19972f7083ed4c3c9c8b00c96e906b45\",\n" +
                "\t\t\t\"ebikeId\": \"f414a5dbd4a64107a74cca24a3582c28\",\n" +
                "\t\t\t\"name\": \"闽C747475\"\n" +
                "\t\t}],\n" +
                "\t\"parentId\": \"b5980cac5fc14b11113e43ebf9fe673d\"\n" +
                "}";

        Folder item = new Gson().fromJson(str, Folder.class);
        ArrayList<Folder> folders = new ArrayList<>();
        folders.add(item);
        selectTreeView.showFolders(folders);
    }

}
