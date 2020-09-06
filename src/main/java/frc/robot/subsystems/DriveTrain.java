/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// import edu.wpi.cscore.CvSink;
// import edu.wpi.cscore.CvSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
 
  private final WPI_VictorSPX m_leftMotor1;
  private final WPI_VictorSPX m_rightMotor1;
  private final WPI_VictorSPX m_leftMotor2;
  private final WPI_VictorSPX m_rightMotor2;
  private final DifferentialDrive m_drive;

  private final Encoder m_encoder1;
  private final Encoder m_encoder2;

  private final Gyro m_gyro;
  private double error;
  private double heading;
  private boolean setHeading;

  private NetworkTable table; 
  private NetworkTableEntry tx;
  private NetworkTableEntry ty;
  private NetworkTableEntry ta;

  public DriveTrain() {
    m_leftMotor1 = new WPI_VictorSPX(Constants.DRIVE_LEFT_VICTORSPX0);
    m_leftMotor2 = new WPI_VictorSPX(Constants.DRIVE_LEFT_VICTORSPX1);
    m_rightMotor1 = new WPI_VictorSPX(Constants.DRIVE_RIGHT_VICTORSPX0);
    m_rightMotor2 = new WPI_VictorSPX(Constants.DRIVE_RIGHT_VICTORSPX1);
    m_leftMotor2.follow(m_leftMotor1);
    m_rightMotor2.follow(m_rightMotor1);
    
    m_encoder1 = new Encoder(Constants.ENCODER_PORT0, Constants.ENCODER_PORT1);
    m_encoder2 = new Encoder(Constants.ENCODER_PORT2, Constants.ENCODER_PORT3);
    m_encoder1.setDistancePerPulse(Math.PI * 6 / 2048);
    m_encoder2.setDistancePerPulse(Math.PI * 6 / 2048);
    m_encoder1.reset();
    m_encoder2.reset();

    m_gyro = new ADXRS450_Gyro();
    m_gyro.reset();
    m_gyro.calibrate();


    m_drive = new DifferentialDrive(m_leftMotor1, m_rightMotor1);
    heading = 0;
    error = 0;
    setHeading = true;

    table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("ledMode").setNumber(1);
    table.getEntry("camMode").setNumber(1);
    CameraServer.getInstance().startAutomaticCapture();
       
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public void arcadeDrive(double move, double rotate) {
    error = m_encoder1.getRate() - m_encoder2.getRate();

    if(Math.abs(rotate)  < 0.2 && Math.abs(move) > 0.2){
      m_drive.tankDrive(move + (0.0002 * error), move - (0.0002 * error));
    }
    else if(Math.abs(rotate) > 0.2 && Math.abs(move) > 0.2){
      m_drive.arcadeDrive(move, rotate);
    }
    else if(Math.abs(move) < 0.2 && Math.abs(rotate) > 0.2){
      m_drive.arcadeDrive(move, rotate);
    }



    // if(Math.abs(rotate) < 0.2) {
    //   setHeading = false;
    // }
    // else {
    //   setHeading = true;
    // }
    
    // if(setHeading){
    //   heading = m_gyro.getAngle();
    // }

    // error = m_gyro.getAngle() - heading; 

    // // if(Math.abs(rotate) > 0.2 && Math.abs(move) > 0.2) {
    // //   m_drive.tankDrive(move - rotate, move + rotate);
    // //   setHeading = false;
    // // }
    // // else if(Math.abs(rotate) < 0.2 && Math.abs(move) > 0.2) {
    // //   m_drive.tankDrive(move + (0.02 * error), move - (0.02 * error));
    // //   setHeading = false;
    // // }
    // // else if(Math.abs(rotate) > 0.2 && Math.abs(move) < 0.2) {
    // //   m_drive.tankDrive(-rotate, 1.2 * rotate);
    // //   setHeading = true;
    // // }
  
    // if(Math.abs(rotate) < 0.2 && Math.abs(move) > 0.2 ){
    //   m_drive.tankDrive(move + (0.02 * error), move - (0.02 * error));
    // }
    // else if(Math.abs(rotate) > 0.2 && Math.abs(move) > 0.2) {
    //   heading += 2 * rotate;                     // Changes the heading based on rotation input and attempts
    //   error = m_gyro.getAngle() - heading;   // to "drive straight" towards new heading
    //   if(rotate < 0) {
    //     m_drive.tankDrive(move + (0.05 * error), move - (0.05 * error));
    //   }
    //   else {
    //     m_drive.tankDrive(move + (0.02 * error), move - (0.02 * error));
    //   }
    // }

    // if(Math.abs(move) < 0.2 && Math.abs(rotate) > 0.2) {
    //     m_drive.tankDrive(-rotate, 1.1 * rotate);
    // }
    

    SmartDashboard.putBoolean("setHeading", setHeading);
    SmartDashboard.putNumber("Encoder 1", m_encoder1.getRate());
    SmartDashboard.putNumber("Encoder 2", m_encoder2.getRate());
    SmartDashboard.putNumber("Angle", m_gyro.getAngle());
    SmartDashboard.putNumber("Heading", heading);
    SmartDashboard.putNumber("Error", error);
    SmartDashboard.putNumber("Move", move);
    SmartDashboard.putNumber("Rotate", rotate);
  }

  public void drive(double move, double turn){
    if(Math.abs(move) > 0.2 && Math.abs(turn) < 0.2){
      m_drive.tankDrive(move * 0.75, move);
    }
    else{
      m_drive.arcadeDrive(move, -turn);
    }
  }

  public void autoDrive(double move, double turn) {
    m_drive.arcadeDrive(move, -turn);
  }

  public void visionDrive(boolean button) {
    
    boolean isAPressed = button;
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");

    if(isAPressed){
      //reset gyro readings
      setHeading = true;
      heading = 0;
      error = 0;
      //accesses network table
      table.getEntry("camMode").setNumber(0);
      table.getEntry("ledMode").setNumber(3);

      //read values periodically
      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      double area = ta.getDouble(0.0);
        
      //post to smart dashboard periodically
      SmartDashboard.putNumber("LimelightX", x);
      SmartDashboard.putNumber("LimelightY", y);
      SmartDashboard.putNumber("LimelightArea", area);  

      double move = 0;
      double turn = 0;

      //moves robot based on crosshair position
      if (x > 4)
        turn = .5;
      if (x < -4)
        turn = -.5;
      if ( y > 2)
        move = -.5;
      if ( y < -2)
        move = .5;
      
      this.drive(-move, turn);
    }
    else {
      table.getEntry("ledMode").setNumber(1);
      table.getEntry("camMode").setNumber(1);
    }
    
  }

  public void stop() {
    m_drive.arcadeDrive(0, 0);
  }

  public void resetEncoders() {
    m_encoder1.reset();
    m_encoder2.reset();
  }

  public void resetGyro() {
    heading = 0;
    error = 0;
  }

  public void calibrateGyro() {
    m_gyro.reset();
    m_gyro.calibrate();
  }

  public void resetError() {
    heading = 0;
    error = 0;
  }

  public void resetSetHeading() {
    setHeading = true;
  }
}
