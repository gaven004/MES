/*
 MES数据库补丁
 flowmeter_dat增加4个属性
 Date: 2020-09-25
*/

ALTER TABLE [dbo].[flowmeter_dat]
    ADD
        [high_alarm] int NULL,
        [low_alarm] int NULL,
        [atc_alarm] int NULL,
        [sys_alarm] int NULL;
GO