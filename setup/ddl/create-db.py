# -*- coding: utf-8 -*-

import os

dbUser = 'root'
dbPass = 'root'

fileList = [
    # 'portaldb.sql',
    'com_audit_trail.sql',
    'create_user.sql',
    'delete_user.sql',
    'insert_role.sql',
    'produce_ext_app_list.sql',
    'update_role.sql',
    'update_user.sql'
]

for sqlFile in fileList:
    cmd = 'mysql -u%s -p%s -D portaldb < %s' % (dbUser, dbPass, sqlFile)
    print "Executing " + cmd + " ..."
    os.system(cmd)

