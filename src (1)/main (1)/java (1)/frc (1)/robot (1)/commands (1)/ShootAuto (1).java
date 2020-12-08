/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class ShootAuto extends CommandBase {
  private final ShooterSubsystem m_shooter;
  private final Indexer m_indexer;
  private final DriveTrain m_drive;
  private Timer time;

  // private NetworkTable table; 
  // private NetworkTableEntry tx; 
  // private NetworkTableEntry ty; 
  // private NetworkTableEntry ta; 
  // private double move;
  // private double turn;

  public ShootAuto(ShooterSubsystem shooter, Indexer indexer, DriveTrain drive) {
    m_shooter = shooter;
    m_indexer = indexer;
    m_drive = drive;
    time = new Timer();
    // move = 0;
    // turn = 0;

    // table = NetworkTableInstance.getDefault().getTable("limelight");
    // table.getEntry("ledMode").setNumber(1);
    // table.getEntry("camMode").setNumber(1);

    addRequirements(m_shooter, m_indexer, m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // tx = table.getEntry("tx");
    // ty = table.getEntry("ty");
    // ta = table.getEntry("ta");

    // double x = tx.getDouble(0.0);
    // double y = ty.getDouble(0.0);
    // double area = ta.getDouble(0.0);
    // double xError = x - 2;
    // double yError = y - 2;

    // if(time.get() < 4){
    //   table.getEntry("camMode").setNumber(0);
    //   table.getEntry("ledMode").setNumber(3);

    //   if (x > 5)
    //     turn = .5;
    //   if (x < -5)
    //     turn = -.5;
    //   if ( y > 3)
    //     move = -.5;
    //   if ( y < -3)
    //     move = .5;

    //   m_drive.arcadeDrive(move, turn);
    // }
    // else {
    //   table.getEntry("camMode").setNumber(1);
    //   table.getEntry("ledMode").setNumber(1);
      
    // }

    // if(time.get() == 4) {
    //   m_drive.resetError();
    //   m_drive.resetSetHeading();
    // }

    if(time.get() < 6){
      m_shooter.shoot(0.79);
      if(time.get() > 2) {
        m_indexer.in(.5);
      }
    }
    else {
      m_shooter.shoot(0);
      m_indexer.in(0);
    }

    if(time.get() > 5 && time.get() < 8) {
      m_drive.arcadeDrive(0.5, 0);
    }
    else {
      m_drive.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
