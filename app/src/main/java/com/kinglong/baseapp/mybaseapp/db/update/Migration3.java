package com.kinglong.baseapp.mybaseapp.db.update;

import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2;
import com.kinglong.baseapp.mybaseapp.db.DbFlowDataBase;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * Description:
 */
@Migration(version = 3, database = DbFlowDataBase.class)
public class Migration3 extends AlterTableMigration<ProjectInfoV2> {

    public Migration3(Class<ProjectInfoV2> table) {
        super(table);
    }

    @Override
    public void onPreMigrate() {
        addColumn(SQLiteType.TEXT, "UserId2");
    }
}
