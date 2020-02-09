/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
  /**
   * Creates a new DriveTrain.
   */

  private final Talon m_leftMotor;
  private final Talon m_rightMotor;
  private final DifferentialDrive m_drive;
  private final Encoder m_encoder;
  private final PIDController m_controller;

  private final double kP = 0.0;
  private final double kI = 0.0;
  private final double kD = 0.0;

  public DriveTrain() {
    m_leftMotor = new Talon(Constants.DRIVETRAIN_LEFT_TALON);
    m_rightMotor = new Talon(Constants.DRIVETRAIN_RIGHT_TALON);
    m_encoder = new Encoder(Constants.encoderPort0, Constants.encoderPort1);
    m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_controller = new PIDController(kP, kI, kD);
    m_controller.setTolerance(0.5);
    m_controller.enableContinuousInput(-200, 200);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double move, double rotate) {
    m_drive.arcadeDrive(move, rotate);
  }

  public void drive(double distance) {
    m_controller.setSetpoint(distance);
    double pidOut = m_controller.calculate(m_encoder.getDistance(), m_controller.getSetpoint());
    arcadeDrive(pidOut, 0);
    SmartDashboard.putNumber("Encoder", m_encoder.getDistance());
  }

  public void stop() {
    m_drive.arcadeDrive(0, 0);
  }

  public void resetEncoder() {
    m_encoder.reset();
  }

  public void resetPID() {
    m_controller.reset();
  }
}
