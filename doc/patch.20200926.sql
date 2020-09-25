IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flowmeter_dat]') AND type IN ('U'))
    DROP TABLE [dbo].[flowmeter_dat]
GO

CREATE TABLE [dbo].[flowmeter_dat] (
   [id] int  IDENTITY(5000,1) NOT NULL,
   [ctime] datetime DEFAULT (getdate()) NOT NULL,
   [device_id] int  NOT NULL,
   [q] real  NULL,
   [v] real  NULL,
   [q_pct] real  NULL,
   [ratio] real  NULL,
   [accq_pos] float(53)  NULL,
   [accq_neg] float(53)  NULL,
   [q_unit] int  NULL,
   [accq_unit] int  NULL,
   [high_alarm] int NULL,
   [low_alarm] int NULL,
   [atc_alarm] int NULL,
   [sys_alarm] int NULL
)
GO

ALTER TABLE [dbo].[flowmeter_dat] SET (LOCK_ESCALATION = TABLE)
GO

CREATE NONCLUSTERED INDEX [idx_ctime]
    ON [dbo].[flowmeter_dat] (
                              [ctime] ASC
        )
GO

ALTER TABLE [dbo].[flowmeter_dat] ADD CONSTRAINT [PK__flowmete__3213E83FEFCB60C8] PRIMARY KEY CLUSTERED ([id])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
GO
