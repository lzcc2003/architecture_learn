map阶段。context.write出去两个表的每一行数据。key为userid，value：各自表的数据组拼
pv:pageid,userid
user:userid,age
通过shuffle和order。然后在reduce阶段做join操作，输出page，age
