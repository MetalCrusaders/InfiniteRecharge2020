/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveArcadeAuto100 extends CommandBase {
  private final DriveTrain m_drive;

  final double move = 0.5;
  final double rotate = 0;
  /**
   * Creates a new DriveArcadeAuto.
   */
  public DriveArcadeAuto100(DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;

    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //long deltaTime = System.nanoTime() - time;
    double dist = m_drive.getEncoder1Distance();
    SmartDashboard.putNumber("Encoder", dist);
    // double error = 100 - Math.abs(dist);
    // double integral = integral + (error * )
    // double P = 0.005;
    if (Math.abs(dist) < 97.5) {
      m_drive.arcadeDrive(move, rotate);
    }
    else if(Math.abs(dist) > 102.5) {
      m_drive.arcadeDrive(-move, rotate);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
