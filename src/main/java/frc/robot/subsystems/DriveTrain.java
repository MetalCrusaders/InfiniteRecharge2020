/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

   private final Talon m_leftMotor;
   private final Talon m_rightMotor;
   private final DifferentialDrive m_drive;

  public DriveTrain() {
    m_leftMotor = new Talon(Constants.DRIVETRAIN_LEFT_TALON);
    m_rightMotor = new Talon(Constants.DRIVETRAIN_RIGHT_TALON);

    m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double move, double rotate) {
    m_drive.arcadeDrive(move, rotate);
  }

  public void stop() {
    m_drive.arcadeDrive(0, 0);
  }
}
